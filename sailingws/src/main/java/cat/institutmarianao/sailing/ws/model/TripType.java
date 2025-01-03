package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.validation.groups.OnTripTypeCreate;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripTypeUpdate;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* JPA annotations */
@Entity
@Table(name = "trip_types")

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripType implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	public static final String GROUP = "GROUP";
	public static final String PRIVATE = "PRIVATE";

	public enum Category {
		GROUP, PRIVATE
	}

	/* Validation */
	/* JPA */
	@Id
	@Null(groups = OnTripTypeCreate.class)
	@NotNull(groups = OnTripTypeUpdate.class)
	@PositiveOrZero
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	/* JPA */
	@NotBlank
	@Column(nullable = false)
	private String title;

	/* Validation */
	/* JPA */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;

	/* Validation */
	/* JPA */
	@NotBlank
	@Size(max = MAX_DESCRIPTION)
	@Column(nullable = false)
	private String description;

	/* Validation */
	/* JPA */
	@Positive
	@Column(nullable = false)
	private double price;

	/* JPA */
	@ElementCollection
	@CollectionTable(name = "trip_type_departures", joinColumns = @JoinColumn(name = "trip_type_id",nullable = false))
	@Column(name = "departure",nullable = false)
	@Temporal(TemporalType.TIME)
	private List<Date> departures;

	/* Validation */
	/* JPA */
	@Positive
	private int duration;

	/* Validation */
	/* JPA */
	@Positive
	@Column(name = "max_places",nullable = false)
	private int maxPlaces;
}

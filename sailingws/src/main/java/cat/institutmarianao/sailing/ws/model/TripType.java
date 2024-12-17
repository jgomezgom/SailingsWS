package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	/* JPA */
	@Column(name = "title")
	private String title;

	/* Validation */
	/* JPA */
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;

	/* Validation */
	/* JPA */
	@Column(name = "description")
	private String description;

	/* Validation */
	/* JPA */
	@Column(name = "price")
	private double price;

	/* JPA */
	@ElementCollection
	@CollectionTable(name = "trip_type_departures", joinColumns = @JoinColumn(name = "trip_type_id"))
	@Column(name = "departure")
	@Temporal(TemporalType.TIME)
	private List<Date> departures;

	/* Validation */
	/* JPA */
	@Column(name = "duration")
	private int duration;

	/* Validation */
	/* JPA */
	@Column(name = "max_places")
	private int maxPlaces;
}

package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;

import cat.institutmarianao.sailing.ws.validation.groups.OnActionCreate;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionUpdate;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JPA annotations */
@Entity
@Table(name = "actions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)

/* Mapping JPA Indexes */
/* JPA Inheritance strategy is single table */
/*
 * Maps different JPA objects depDONEing on his type attribute (Opening,
 * Assignment, Intervention or Close)
 */
/* Lombok */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String BOOKING = "BOOKING";
	public static final String RESCHEDULING = "RESCHEDULING";
	public static final String CANCELLATION = "CANCELLATION";
	public static final String DONE = "DONE";

	public enum Type {
		BOOKING, RESCHEDULING, CANCELLATION, DONE
	}

	/* Validation */
	/* JPA */
	@Id
	@Null(groups = {OnActionCreate.class,OnTripCreate.class})
	@NotNull(groups = OnActionUpdate.class)
	@PositiveOrZero(groups = OnActionUpdate.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	/* Validation */
	/* JPA */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, insertable = false, updatable = false)
	/* Lombok */
	@NonNull
	protected Type type;

	/* Validation */
	/* JPA */
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "performer_username",nullable = false)
	protected User performer;

	/* JPA */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	protected Date date = new Date();

	/* Validation */
	/* JPA */
	@JoinColumn(nullable = false)
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	/* JSON */
	protected Trip trip;

	/* JPA */
	private String comments;
}

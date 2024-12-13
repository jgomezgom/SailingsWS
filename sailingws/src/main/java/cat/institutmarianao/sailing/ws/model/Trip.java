package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* JPA annotations */
/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String RESERVED = "RESERVED";
	public static final String RESCHEDULED = "RESCHEDULED";
	public static final String CANCELLED = "CANCELLED";
	public static final String DONE = "DONE";

	public enum Status {
		RESERVED, RESCHEDULED, CANCELLED, DONE
	}

	/* Validation */
	/* JPA */
	/* Lombok */
	@EqualsAndHashCode.Include
	/* JSON */
	private Long id;

	/* JPA */
	private TripType type;

	/* Validation */
	/* JPA */
	private Client client;

	private int places;

	/* Validation */
	/* JPA */
	private List<@Valid Action> tracking;

	/* JPA */
	/* Hibernate */
	@Formula("(SELECT CASE a.type WHEN '" + Action.BOOKING + "' THEN '" + Trip.RESERVED + "' WHEN '"
			+ Action.RESCHEDULING + "' THEN '" + Trip.RESCHEDULED + "' WHEN '" + Action.CANCELLATION + "' THEN '"
			+ Trip.CANCELLED + "' WHEN '" + Action.DONE + "' THEN '" + Trip.DONE + "' ELSE NULL END FROM actions a "
			+ " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
			+ " WHERE last_action.trip_id=a.trip_id AND last_action.trip_id=id ))")
	// Lombok
	@Setter(AccessLevel.NONE)
	private Status status;

	/* Validation */
	/* JPA */
	private Date date;

	/* JPA */
	private Date departure;
}

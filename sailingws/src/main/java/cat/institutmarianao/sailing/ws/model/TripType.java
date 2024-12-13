package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* JPA annotations */
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
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	/* Validation */
	private String title;

	/* Validation */
	/* JPA */
	private Category category;

	/* Validation */
	private String description;

	/* Validation */
	private double price;

	/* JPA */
	private List<Date> departures;

	/* Validation */
	private int duration;

	/* Validation */
	/* JPA */
	private int maxPlaces;
}

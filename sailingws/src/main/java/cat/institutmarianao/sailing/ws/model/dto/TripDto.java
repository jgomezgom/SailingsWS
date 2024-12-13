package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	/* JSON */
	@Null(groups = OnTripCreate.class)
	private Long id;

	/* JPA */
	@NotNull
	@PositiveOrZero
	private Long typeId;

	/* Validation */
	@NotBlank
	private String clientUsername;

	private int places;

	/* Lombok */
	private Status status;

	/* Validation */
	/* JSON */
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.DATE_PATTERN)
	private Date date;

	/* JSON */
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.TIME_PATTERN)
	private Date departure;
}

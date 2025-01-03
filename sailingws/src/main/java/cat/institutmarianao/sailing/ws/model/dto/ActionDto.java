package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Action.Type;
import cat.institutmarianao.sailing.ws.model.User;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionCreate;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionUpdate;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* JSON annotations */
/*
 * Maps JSON data to OpeningDto, AssignmentDto, InterventionDto or CloseDto instance
 * depending on property type
 */
/* Validation */
/* Lombok */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = BookingDto.class, name = Action.BOOKING),
		@JsonSubTypes.Type(value = ReschedulingDto.class, name = Action.RESCHEDULING),
		@JsonSubTypes.Type(value = CancellationDto.class, name = Action.CANCELLATION),
		@JsonSubTypes.Type(value = DoneDto.class, name = Action.DONE) })
public abstract class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	@Null(groups = {OnActionCreate.class,OnTripCreate.class})
	@NotNull(groups = OnActionUpdate.class)
	@PositiveOrZero
	protected Long id;

	/* Validation */
	/* Lombok */
	@NonNull
	@NotNull
	protected Type type;

	/* Validation */
	@NotBlank
	@Size(min = User.MIN_USERNAME)
	protected String performer;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.TIMESTAMP)
	protected Date date = new Date();

	/* Validation */
	/* JSON */
	@NotNull
	@PositiveOrZero
	protected Long tripId;

	private String comments;
}

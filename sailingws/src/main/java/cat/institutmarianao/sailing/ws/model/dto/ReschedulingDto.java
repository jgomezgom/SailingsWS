package cat.institutmarianao.sailing.ws.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.Action.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReschedulingDto extends ActionDto {
	private static final long serialVersionUID = 1L;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.DATE_PATTERN)
	private Date newDate;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.TIME_PATTERN)
	private Date newDeparture;

	public ReschedulingDto() {
		super(Type.RESCHEDULING);
	}
}

package cat.institutmarianao.sailing.ws.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Validation */
/* JPA annotations */
@Entity
@DiscriminatorValue(Action.RESCHEDULING)
/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rescheduling extends Action {
	private static final long serialVersionUID = 1L;

	/* Validation */
	/* JPA */
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "new_date")
	private Date newDate;

	/* Validation */
	/* JPA */
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "new_departure")
	private Date newDeparture;
}

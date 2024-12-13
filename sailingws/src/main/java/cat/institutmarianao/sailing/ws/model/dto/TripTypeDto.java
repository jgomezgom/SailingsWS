package cat.institutmarianao.sailing.ws.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripTypeCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TripTypeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Validation */
	/* Lombok */
	@EqualsAndHashCode.Include
	@Null(groups = OnTripTypeCreate.class)
	private Long id;

	/* Validation */
	@NotBlank
	private String title;

	/* Validation */
	@NotBlank
	private Category category;

	/* Validation */
	@NotBlank
	@Size(max = TripType.MAX_DESCRIPTION)
	private String description;

	/* Validation */
	@NotNull
	@Positive
	private double price;

	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SailingWsApplication.TIME_PATTERN)
	private List<Date> departures;

	/* Validation */
	@NotNull
	@Positive
	private int duration;

	/* Validation */
	@NotNull
	@Positive
	private int maxPlaces;
}

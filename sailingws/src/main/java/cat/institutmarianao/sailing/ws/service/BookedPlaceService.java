package cat.institutmarianao.sailing.ws.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface BookedPlaceService {

	List<BookedPlace> bookedPlacesFromDate(@NotNull @Positive Long id,
			@NotNull @DateTimeFormat(pattern = SailingWsApplication.DATE_PATTERN) Date date);
	
	BookedPlace bookedPlacesFromDateAndDeparture(@NotNull @Positive Long id,
			@NotNull @DateTimeFormat(pattern = SailingWsApplication.DATE_PATTERN) Date date,
			@NotNull @DateTimeFormat(pattern = SailingWsApplication.TIME_PATTERN) Date departure);
}

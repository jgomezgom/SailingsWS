package cat.institutmarianao.sailing.ws.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.repository.BookedPlaceRepository;
import cat.institutmarianao.sailing.ws.service.BookedPlaceService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class BookedPlaceServiceimpl implements BookedPlaceService {

	@Autowired
	private BookedPlaceRepository bookedPlaceRepository;

	@Override
	public List<BookedPlace> bookedPlacesFromDate(@NotNull @Positive Long tripTypeId, @NotNull @DateTimeFormat(pattern = SailingWsApplication.DATE_PATTERN) Date date) {
		return bookedPlaceRepository.findByIdTripTypeIdAndIdDate(tripTypeId, date);
	}

	@Override
	public BookedPlace bookedPlacesFromDateAndDeparture(@NotNull @Positive Long tripTypeId, @NotNull @DateTimeFormat(pattern = SailingWsApplication.DATE_PATTERN) Date date,
			@NotNull @DateTimeFormat(pattern = SailingWsApplication.TIME_PATTERN) Date departure) {
		BookedPlace nullBooked= new BookedPlace();
		nullBooked.setBookedPlaces(0);
		return bookedPlaceRepository.findByIdTripTypeIdAndIdDateAndIdDeparture(tripTypeId, date, departure).orElse(nullBooked);
	}

}

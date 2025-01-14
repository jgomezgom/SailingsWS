package cat.institutmarianao.sailing.ws.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.error.utils.DepartureChecker;
import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.service.BookedPlaceService;
import cat.institutmarianao.sailing.ws.service.TripService;
import cat.institutmarianao.sailing.ws.specifications.TripWithCategory;
import cat.institutmarianao.sailing.ws.specifications.TripWithClient;
import cat.institutmarianao.sailing.ws.specifications.TripWithDate;
import cat.institutmarianao.sailing.ws.specifications.TripWithDeparture;
import cat.institutmarianao.sailing.ws.specifications.TripWithPlaces;
import cat.institutmarianao.sailing.ws.specifications.TripWithStatus;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private BookedPlaceService bookedPlaceService;

	@Autowired
	private MessageSource messageSource;

	@Override
	public Page<Trip> findAll(Category category, String clientUsername, Integer places, Status status, Date fromDate,
			Date toDate, Date fromDeparture, Date toDeparture, Pageable pageable) {

		Specification<Trip> spec = Specification.where(new TripWithCategory(category))
				.and(new TripWithClient(clientUsername)).and(new TripWithPlaces(places)).and(new TripWithStatus(status))
				.and(new TripWithDate(fromDate, toDate)).and(new TripWithDeparture(fromDeparture, toDeparture));
		return tripRepository.findAll(spec, pageable);
	}

	@Override
	public List<Trip> findAllByClientUsername(@NotBlank String username) {
		return tripRepository.findByClientUsername(username);
	}

	@Override
	@Validated(OnTripCreate.class)
	public Trip save(@NotNull @Valid Trip trip) {

		checkPlaces(trip);
		DepartureChecker.check(trip, null, messageSource);

		return tripRepository.saveAndFlush(trip);
	}

	private void checkPlaces(Trip trip) {
		BookedPlace place = bookedPlaceService.bookedPlacesFromDateAndDeparture(trip.getType().getId(), trip.getDate(),
				trip.getDeparture());
		int reservedPlaces = (int) place.getBookedPlaces();

		if (trip.getType().getMaxPlaces() - (trip.getPlaces() + reservedPlaces) < 0)
			throw new ConstraintViolationException(messageSource.getMessage("error.TripService.places",
					new Object[] { trip.getType().getMaxPlaces() - reservedPlaces }, LocaleContextHolder.getLocale()),
					null);
	}

	@Override
	public Trip findById(@NotNull @Positive Long id) {
		return tripRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}

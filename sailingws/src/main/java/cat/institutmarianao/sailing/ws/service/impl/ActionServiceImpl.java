package cat.institutmarianao.sailing.ws.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.error.utils.DepartureChecker;
import cat.institutmarianao.sailing.ws.exception.ForbiddenException;
import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Booking;
import cat.institutmarianao.sailing.ws.model.Cancellation;
import cat.institutmarianao.sailing.ws.model.Rescheduling;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.service.ActionService;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionCreate;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private MessageSource messageSource;

	@Value("${sailingws.max.hours.difference}")
	private int maxHoursDifference;

	@Override
	@Validated(OnActionCreate.class)
	public Action saveAction(@Valid Action action) throws ParseException {

		checkErrors(action);

		return actionRepository.saveAndFlush(action);
	}

	@Override
	public List<Action> findTrackingByTripId(@NotNull @Positive Long tripId) {
		return actionRepository.findByTripId(tripId);
	}

	private void checkErrors(Action action) throws ParseException {
		Trip trip = action.getTrip();

		if (trip.getStatus().equals(Status.CANCELLED) || trip.getStatus().equals(Status.DONE)) {
			throw new ConstraintViolationException(messageSource.getMessage("error.Tracking.action.already.finished",
					null, LocaleContextHolder.getLocale()), null);
		}

		if (action instanceof Booking) {
			throw new ConstraintViolationException(messageSource.getMessage("error.Tracking.action.already.booking",
					null, LocaleContextHolder.getLocale()), null);
		}

		if (action instanceof Cancellation) {
			checkPerformer(action, trip);
			checkHours(trip);
		}
		if (action.getTrip().getType().getCategory().equals(Category.GROUP)
				&& action instanceof Rescheduling rescheduling) {
			DepartureChecker.check(trip, rescheduling.getNewDeparture(), messageSource);
		}
	}

	private void checkPerformer(Action action, Trip trip) {
		if (!action.getPerformer().equals(trip.getClient())) {
			throw new ForbiddenException(messageSource.getMessage("error.Tracking.action.forbidden.performer", null,
					LocaleContextHolder.getLocale()));
		}

	}

	private void checkHours(Trip trip) throws ParseException {
		SimpleDateFormat sdfDate = new SimpleDateFormat(SailingWsApplication.DATE_PATTERN);
		SimpleDateFormat sdfTime = new SimpleDateFormat(SailingWsApplication.TIME_PATTERN);
		SimpleDateFormat sdfDateTime = new SimpleDateFormat(SailingWsApplication.DATE_TIME_PATTERN);

		Date tripDate = sdfDateTime.parse(sdfDate.format(trip.getDate()) + " " + sdfTime.format(trip.getDeparture()));
		long difference = Duration.ofMillis(tripDate.getTime() - new Date().getTime()).toHours();
		if (difference < maxHoursDifference) {
			throw new ConstraintViolationException(messageSource.getMessage(
					"error.Tracking.action.cancellation.date.difference", null, LocaleContextHolder.getLocale()), null);
		}
	}

}

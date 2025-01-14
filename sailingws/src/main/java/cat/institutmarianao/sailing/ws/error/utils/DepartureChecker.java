package cat.institutmarianao.sailing.ws.error.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.ConstraintViolationException;

public class DepartureChecker {

	private DepartureChecker() {
	}

	public static void check(Trip trip, Date departure, MessageSource messageSource) {
		if (trip.getType().getCategory().equals(Category.GROUP)) {
			List<Date> departures = trip.getType().getDepartures();
			if (departure != null)
				checkDeparture(departure, messageSource, departures);

			checkDeparture(trip.getDeparture(), messageSource, departures);
		}
	}

	private static void checkDeparture(Date departure, MessageSource messageSource, List<Date> departures) {
		if (!departures.contains(departure)) {
			SimpleDateFormat sdf = new SimpleDateFormat(SailingWsApplication.TIME_PATTERN);
			List<String> depStr = departures.stream().map(sdf::format).toList();
			throw new ConstraintViolationException(messageSource.getMessage("error.Departure.out.range",
					new Object[] { String.join(" | ", depStr) }, LocaleContextHolder.getLocale()), null);
		}
	}
}

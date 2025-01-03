package cat.institutmarianao.sailing.ws.service;

import java.text.ParseException;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ActionService {

	Action saveAction(@Valid Action action) throws ParseException;

	List<Action> findTrackingByTripId(@NotNull @Positive Long tripId);
}

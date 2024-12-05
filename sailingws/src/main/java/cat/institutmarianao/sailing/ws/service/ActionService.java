package cat.institutmarianao.sailing.ws.service;

import cat.institutmarianao.sailing.ws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ActionService {

	Action saveAction(@Valid Action action);

	Iterable<Action> findTrackingByTripId(@NotNull @Positive Long tripId);
}

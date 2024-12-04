package cat.institutmarianao.sailing.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.service.ActionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public Action saveAction(@NotNull @Valid Action action) {
		return actionRepository.saveAndFlush(action);
	}

	@Override
	public Iterable<Action> findTrackingByTripId(@NotNull @Positive Long tripId) {
		return actionRepository.findByTripId(tripId);
	}

}

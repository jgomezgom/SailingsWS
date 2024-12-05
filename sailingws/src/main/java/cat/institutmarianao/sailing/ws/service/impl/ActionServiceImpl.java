package cat.institutmarianao.sailing.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.service.ActionService;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	@Validated(OnActionCreate.class)
	public Action saveAction(@Valid Action action) {
		return actionRepository.saveAndFlush(action);
	}

	@Override
	public Iterable<Action> findTrackingByTripId(@NotNull @Positive Long tripId) {
		return actionRepository.findByTripId(tripId);
	}

}

package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Action.Type;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
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

	@Override
	@Validated(OnActionCreate.class)
	public Action saveAction(@Valid Action action) {

		if (action.getType().equals(Type.BOOKING))
			throw new ConstraintViolationException(null);

		Trip trip = action.getTrip();

		if (trip.getStatus().equals(Status.CANCELLED) || trip.getStatus().equals(Status.DONE))
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "sad");

		return actionRepository.saveAndFlush(action);
	}

	@Override
	public List<Action> findTrackingByTripId(@NotNull @Positive Long tripId) {
		return actionRepository.findByTripId(tripId);
	}

}

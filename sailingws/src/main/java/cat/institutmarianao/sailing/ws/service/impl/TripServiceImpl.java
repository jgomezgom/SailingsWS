package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.service.TripService;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepository;

	@Override
	public List<Trip> findAll() {
		return tripRepository.findAll();
	}

	@Override
	public List<Trip> findAllByClientUsername(@NotBlank String username) {
		return tripRepository.findByClientUsername(username);
	}

	@Override
	@Validated(OnTripCreate.class)
	public Trip save(@NotNull @Valid Trip trip) {
		return tripRepository.saveAndFlush(trip);
	}

}

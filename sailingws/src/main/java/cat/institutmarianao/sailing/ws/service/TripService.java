package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface TripService {

	List<Trip> findAll();

	List<Trip> findAllByClientUsername(@NotBlank String username);

	Trip save(@NotNull @Valid Trip trip);

	Trip findById(@NotNull @Positive Long id);

}

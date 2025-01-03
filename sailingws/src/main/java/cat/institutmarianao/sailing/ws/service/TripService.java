package cat.institutmarianao.sailing.ws.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface TripService {

	Page<Trip> findAll(Category category,String clientUsername,Integer places,Status status,
						Date fromDate,Date toDate,Date fromDeparture,Date toDeparture, Pageable pageable);

	List<Trip> findAllByClientUsername(@NotBlank String username);

	Trip save(@NotNull @Valid Trip trip);

	Trip findById(@NotNull @Positive Long id);

}

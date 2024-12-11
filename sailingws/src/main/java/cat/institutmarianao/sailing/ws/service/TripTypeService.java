package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface TripTypeService {

	List<TripType> findAllTripTypes();

	List<TripType> findAllTripTypesByCategory(@NotNull Category category);

	List<TripType> findAllGroupTripTypes();

	List<TripType> findAllPrivateTripTypes();

	TripType findById(@NotNull @Positive Long id);
}

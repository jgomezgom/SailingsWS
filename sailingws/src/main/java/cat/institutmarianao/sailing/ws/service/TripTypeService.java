package cat.institutmarianao.sailing.ws.service;

import java.util.List;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;

public interface TripTypeService {

	List<TripType> findAllTripTypes();

	List<TripType> findAllTripTypesByCategory(Category category);

	List<TripType> findAllGroupTripTypes();

	List<TripType> findAllPrivateTripTypes();

	TripType findById(Long id);
}

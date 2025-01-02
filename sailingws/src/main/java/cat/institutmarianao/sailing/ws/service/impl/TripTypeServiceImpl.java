package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripTypeRepository;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class TripTypeServiceImpl implements TripTypeService {

	@Autowired
	private TripTypeRepository tripTypeRepository;

	@Override
	public List<TripType> findAllTripTypes() {
		return tripTypeRepository.findAll();
	}

	@Override
	public List<TripType> findAllTripTypesByCategory(@NotNull Category category) {
		return tripTypeRepository.findByCategory(category);
	}

	@Override
	public List<TripType> findAllGroupTripTypes() {
		return tripTypeRepository.findByCategory(Category.GROUP);
	}

	@Override
	public List<TripType> findAllPrivateTripTypes() {
		return tripTypeRepository.findByCategory(Category.PRIVATE);
	}

	@Override
	public TripType findById(@NotNull @Positive Long id) {
		return tripTypeRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}

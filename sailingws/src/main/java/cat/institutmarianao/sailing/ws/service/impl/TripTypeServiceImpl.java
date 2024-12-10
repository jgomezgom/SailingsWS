package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripTypeRepository;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import cat.institutmarianao.salinig.ws.exception.NotFoundException;

public class TripTypeServiceImpl implements TripTypeService {

	@Autowired
	private TripTypeRepository tripTypeRepository;

	@Override
	public List<TripType> findAllTripTypes() {
		return tripTypeRepository.findAll();
	}

	@Override
	public List<TripType> findAllTripTypesByCategory(Category category) {
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
	public TripType findById(Long id) {
		return tripTypeRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}

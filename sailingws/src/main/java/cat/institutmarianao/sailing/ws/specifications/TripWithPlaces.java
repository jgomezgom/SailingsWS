package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TripWithPlaces implements Specification<Trip>{

	private static final long serialVersionUID = 1L;
	private Integer places;
	

	public TripWithPlaces(Integer places) {
		this.places = places;
	}


	@Override
	public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(places==null) 
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("places"), places);
	}

}

package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TripWithStatus implements Specification<Trip>{

	private static final long serialVersionUID = 1L;
	private Status status;

	public TripWithStatus(Status status) {
		this.status = status;
	}
	@Override
	public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (status == null)
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("status"), status);
	}

}

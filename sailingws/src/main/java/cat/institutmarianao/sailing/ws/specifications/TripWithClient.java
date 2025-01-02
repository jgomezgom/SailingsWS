package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TripWithClient implements Specification<Trip> {

	private static final long serialVersionUID = 1L;
	private String username;
	
	
	public TripWithClient(String username) {
		this.username = username;
	}


	@Override
	public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (username == null)
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("client").get("username"), username);
	}

}

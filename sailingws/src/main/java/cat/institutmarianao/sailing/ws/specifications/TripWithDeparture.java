package cat.institutmarianao.sailing.ws.specifications;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TripWithDeparture implements Specification<Trip>{

	private static final long serialVersionUID = 1L;
	private Date from;
	private Date to;

	public TripWithDeparture(Date from, Date to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (from == null && to == null)
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); 
		
		if (from != null ) {
			if(to != null) return criteriaBuilder.between(root.get("departure"), from, to);
			
			return criteriaBuilder.greaterThanOrEqualTo(root.get("departure"), from);
		}
		return criteriaBuilder.lessThanOrEqualTo(root.get("departure"), to); 
	}

}

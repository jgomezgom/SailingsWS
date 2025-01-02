package cat.institutmarianao.sailing.ws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TripWithCategory implements Specification<Trip>{

	private static final long serialVersionUID = 1L;
	private Category category;

	public TripWithCategory(Category category) {
		this.category = category;
	}

	@Override
	public Predicate toPredicate(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (category == null)
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("type").get("category"), category);
	}

	

}

package cat.institutmarianao.sailing.ws.repository;
/*JPA*/

import java.util.Date;
import java.util.List;
import java.util.Optional;

import cat.institutmarianao.sailing.ws.model.BookedPlace;
import cat.institutmarianao.sailing.ws.model.BookedPlaceCompositeId;

public interface BookedPlaceRepository extends ViewRepository<BookedPlace, BookedPlaceCompositeId> {

	List<BookedPlace> findByIdTripTypeIdAndIdDate(Long id, Date date);
	
	Optional<BookedPlace> findByIdTripTypeIdAndIdDateAndIdDeparture(Long id, Date date, Date departure);
}

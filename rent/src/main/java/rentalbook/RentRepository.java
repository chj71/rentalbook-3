package rentalbook;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RentRepository extends PagingAndSortingRepository<Rent, Long>{
    List<Rent> findByOrderId(Long orderId);

}
package efrei.projectCloud.orderMicroservice.repository;

import efrei.projectCloud.orderMicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}


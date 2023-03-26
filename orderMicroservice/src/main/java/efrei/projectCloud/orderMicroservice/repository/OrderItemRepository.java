package efrei.projectCloud.orderMicroservice.repository;

import efrei.projectCloud.orderMicroservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}


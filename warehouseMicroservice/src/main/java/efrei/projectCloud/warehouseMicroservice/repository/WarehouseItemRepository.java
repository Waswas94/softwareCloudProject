package efrei.projectCloud.warehouseMicroservice.repository;

import efrei.projectCloud.warehouseMicroservice.model.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
}

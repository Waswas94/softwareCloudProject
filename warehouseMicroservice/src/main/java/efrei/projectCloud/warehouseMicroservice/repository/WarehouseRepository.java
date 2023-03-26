package efrei.projectCloud.warehouseMicroservice.repository;

import efrei.projectCloud.warehouseMicroservice.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}


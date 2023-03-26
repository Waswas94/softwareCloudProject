package efrei.projectCloud.warehouseMicroservice.service;

import efrei.projectCloud.warehouseMicroservice.model.Warehouse;
import efrei.projectCloud.warehouseMicroservice.model.WarehouseItem;
import efrei.projectCloud.warehouseMicroservice.repository.WarehouseItemRepository;
import efrei.projectCloud.warehouseMicroservice.repository.WarehouseRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseItemRepository warehouseItemRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseItemRepository warehouseItemRepository) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseItemRepository = warehouseItemRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        Warehouse createdWarehouse = warehouseRepository.save(warehouse);
        warehouse.getWarehouseItems().forEach(warehouseItem -> {
            warehouseItem.setWarehouse(createdWarehouse);
            warehouseItemRepository.save(warehouseItem);
        });
        return createdWarehouse;
    }

    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        return warehouseRepository.findById(id)
                .map(warehouse -> {
                    warehouse.setName(updatedWarehouse.getName());
                    warehouse.setLocation(updatedWarehouse.getLocation());

                    // Remove existing order items
                    warehouse.getWarehouseItems().forEach(warehouseItem -> {
                        warehouseItemRepository.deleteById(warehouseItem.getId());
                    });

                    // Update the order items

                    List<WarehouseItem> newWarehouseItems = updatedWarehouse.getWarehouseItems();
                    newWarehouseItems.forEach(warehouseItem -> {
                        warehouseItem.setWarehouse(warehouse);
                        warehouseItemRepository.save(warehouseItem);
                    });

                    warehouse.setWarehouseItems(newWarehouseItems);

                    return warehouseRepository.save(warehouse);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}


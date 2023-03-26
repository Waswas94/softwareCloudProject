package efrei.projectCloud.inventoryMicroservice.service;

import efrei.projectCloud.inventoryMicroservice.model.InventoryItem;
import efrei.projectCloud.inventoryMicroservice.repository.InventoryItemRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryItemRepository repository;
    public InventoryService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> getAllInventoryItems() {
        return repository.findAll();
    }

    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return repository.findById(id);
    }

    public Optional<InventoryItem> getInventoryItemByProductCode(String productCode) {
        return repository.findByProductCode(productCode);
    }

    public InventoryItem createInventoryItem(InventoryItem inventoryItem) {
        return repository.save(inventoryItem);
    }

    public InventoryItem updateInventoryItem(Long id, InventoryItem updatedInventoryItem) {
        return repository.findById(id)
                .map(inventoryItem -> {
                    inventoryItem.setProductName(updatedInventoryItem.getProductName());
                    inventoryItem.setProductCode(updatedInventoryItem.getProductCode());
                    inventoryItem.setQuantity(updatedInventoryItem.getQuantity());
                    inventoryItem.setPrice(updatedInventoryItem.getPrice());
                    InventoryItem savedItem = repository.save(inventoryItem);
                    return savedItem;
                })
                .orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with id " + id));
    }

    public void deleteInventoryItem(Long id) {
        repository.deleteById(id);
    }
}


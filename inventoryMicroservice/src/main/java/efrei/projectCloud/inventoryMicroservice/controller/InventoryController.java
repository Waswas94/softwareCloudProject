package efrei.projectCloud.inventoryMicroservice.controller;

import efrei.projectCloud.inventoryMicroservice.model.InventoryItem;
import efrei.projectCloud.inventoryMicroservice.service.InventoryKafkaProducer;
import efrei.projectCloud.inventoryMicroservice.service.InventoryService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private InventoryKafkaProducer inventoryKafkaProducer;

    public InventoryController(InventoryService inventoryService, InventoryKafkaProducer inventoryKafkaProducer) {
        this.inventoryService = inventoryService;
        this.inventoryKafkaProducer = inventoryKafkaProducer;

    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        return ResponseEntity.ok(inventoryService.getAllInventoryItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        return inventoryService.getInventoryItemById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with id " + id));
    }

    @GetMapping("/product-code/{productCode}")
    public ResponseEntity<InventoryItem> getInventoryItemByProductCode(@PathVariable String productCode) {
        return inventoryService.getInventoryItemByProductCode(productCode)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("InventoryItem not found with product code " + productCode));
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem inventoryItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventoryItem(inventoryItem));
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem updatedInventoryItem) {
        return ResponseEntity.ok(inventoryService.updateInventoryItem(id, updatedInventoryItem));
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem updatedInventoryItem) {
        InventoryItem updatedItem = inventoryService.updateInventoryItem(id, updatedInventoryItem);

        // Send a message to the Order and Warehouse microservice when the inventory is updated
        inventoryKafkaProducer.sendOrderUpdate("Inventory item " + id + " has been updated.");
        inventoryKafkaProducer.sendWarehouseUpdate("Inventory item " + id + " has been updated.");

        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }
}


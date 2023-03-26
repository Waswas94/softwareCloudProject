package efrei.projectCloud.inventoryMicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderUpdate(String message) {
        kafkaTemplate.send("order_update", message);
    }

    public void sendWarehouseUpdate(String message) {
        kafkaTemplate.send("warehouse_update", message);
    }
}
package efrei.projectCloud.warehouseMicroservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WarehouseKafkaListener {
    @KafkaListener(topics = "warehouse_update", groupId = "warehouse_group")
    public void listen(String message) {
        System.out.println("Received update message: " + message);
    }
}

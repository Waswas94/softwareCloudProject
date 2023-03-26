package efrei.projectCloud.orderMicroservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaListener {

    @KafkaListener(topics = "order_update", groupId = "order_group")
    public void listen(String message) {
        System.out.println("Received update message: " + message);
    }
}

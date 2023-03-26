package efrei.projectCloud.orderMicroservice.service;


import efrei.projectCloud.orderMicroservice.model.Order;
import efrei.projectCloud.orderMicroservice.model.OrderItem;
import efrei.projectCloud.orderMicroservice.repository.OrderItemRepository;
import efrei.projectCloud.orderMicroservice.repository.OrderRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        Order createdOrder = orderRepository.save(order);
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(createdOrder);
            orderItemRepository.save(orderItem);
        });
        return createdOrder;
    }


    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomerName(updatedOrder.getCustomerName());
                    order.setShippingAddress(updatedOrder.getShippingAddress());
                    order.setTotalAmount(updatedOrder.getTotalAmount());
                    order.setOrderDate(updatedOrder.getOrderDate());

                    // Remove existing order items
                    order.getOrderItems().forEach(orderItem -> {
                        orderItemRepository.deleteById(orderItem.getId());
                    });

                    // Update the order items
                    List<OrderItem> newOrderItems = updatedOrder.getOrderItems();
                    newOrderItems.forEach(orderItem -> {
                        orderItem.setOrder(order);
                        orderItemRepository.save(orderItem);
                    });

                    order.setOrderItems(newOrderItems);

                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public void deleteOrder(Long id) {
        /*orderRepository.findById(id)
                .map(order -> {

                    // Remove existing order items
                    order.getOrderItems().forEach(orderItem -> {
                        orderItemRepository.deleteById(orderItem.getId());
                    });
                })
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));*/
        orderRepository.deleteById(id);
    }
}


package edu.uptc.swii.orderquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.orderquery.model.Order;
import edu.uptc.swii.orderquery.utils.JsonUtils;

@Service
public class OrderEventConsumer {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "add-order-topic", groupId = "order-group")
    public void addOrderConsume(String message) {
        message = message.replace("\\", "");
        message = message.substring(1, message.length() - 1);
        System.out.println("Add Order - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Order savedOrder = jsonUtils.fromJson(message, Order.class);
        orderService.addOrder(savedOrder);

    }

    @KafkaListener(topics = "update-order-topic", groupId = "order-group")
    public void updateOrderConsume(String message) {
        System.out.println("Update Order - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Order savedOrder = jsonUtils.fromJson(message, Order.class);
        orderService.updateOrder(savedOrder);
    }

    @KafkaListener(topics = "delete-order-topic", groupId = "order-group")
    public void deleteOrderConsume(String message) {
        System.out.println("Delete Order - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Order savedOrder = jsonUtils.fromJson(message, Order.class);
        orderService.deleteOrder(savedOrder);
    }
}
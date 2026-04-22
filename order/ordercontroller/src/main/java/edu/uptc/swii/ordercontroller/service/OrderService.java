package edu.uptc.swii.ordercontroller.service;

import org.springframework.stereotype.Service;

import edu.uptc.swii.ordercontroller.model.Order;
import edu.uptc.swii.ordercontroller.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;
    private final String ADD_ORDER_TOPIC = "add-order-topic";
    private final String UPDATE_ORDER_TOPIC = "update-order-topic";
    private final String DELETE_ORDER_TOPIC = "delete-order-topic";

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    public Order addOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        orderEventProducer.sendMessage(ADD_ORDER_TOPIC, order);
        return savedOrder;
    }

    public Order updateOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        orderEventProducer.sendMessage(UPDATE_ORDER_TOPIC, order);
        return savedOrder;
    }

    public String deleteOrder(Order order) {
        String message = new String();
        try {
            orderRepository.delete(order);
            orderEventProducer.sendMessage(DELETE_ORDER_TOPIC, order);
            message = "Order deleted: " + order.toString();
        } catch (Exception e) {
            message = "Order can't be deleted!";
            System.err.println(e.getMessage());
        }
        return message;
    }
}

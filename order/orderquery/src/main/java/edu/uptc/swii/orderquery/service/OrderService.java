package edu.uptc.swii.orderquery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uptc.swii.orderquery.model.Order;
import edu.uptc.swii.orderquery.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.insert(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> optOrder = orderRepository.findById(id);
        return optOrder.get();
    }

}

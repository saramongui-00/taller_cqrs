package edu.uptc.swii.orderquery.controller;

import org.springframework.web.bind.annotation.RestController;
import edu.uptc.swii.orderquery.model.Order;
import edu.uptc.swii.orderquery.repository.OrderRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class OrderQueryController {
    private final OrderRepository orderRepository;

    public OrderQueryController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/allorders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/findorderbyid")
    public Order getOrderById(@RequestParam Long id) {
        return orderRepository.findById(id).get();
    }

}

package edu.uptc.swii.ordercontroller.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.ordercontroller.model.Order;
import edu.uptc.swii.ordercontroller.service.OrderService;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addorder")
    public String sendMessageAddOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return order.toString();
    }

    @PostMapping("/updateorder")
    public String sendMessageUpdateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return order.toString();
    }

    @PostMapping("/delorder")
    public String sendMessageDeleteOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order);
    }
}

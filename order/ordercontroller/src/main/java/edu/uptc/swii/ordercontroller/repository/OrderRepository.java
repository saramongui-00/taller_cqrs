package edu.uptc.swii.ordercontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.uptc.swii.ordercontroller.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

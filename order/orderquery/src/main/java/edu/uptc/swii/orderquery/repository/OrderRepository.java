package edu.uptc.swii.orderquery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.uptc.swii.orderquery.model.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {

}

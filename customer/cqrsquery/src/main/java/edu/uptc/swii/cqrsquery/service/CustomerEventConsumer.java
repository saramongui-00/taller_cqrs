package edu.uptc.swii.cqrsquery.service;

import edu.uptc.swii.cqrsquery.dto.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrsquery.model.Customer;
import edu.uptc.swii.cqrsquery.utils.JsonUtils;

@Service
public class CustomerEventConsumer {
    @Autowired
    private CustomerService customerService;
    private JsonUtils jsonUtils;

    public CustomerEventConsumer(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    @KafkaListener(topics = "add-customer-topic", groupId = "customer-group")
    public void addCustomerConsume(String message) {
        System.out.println("Add Customer - Received Message: " + message);
        CustomerRequest customer = jsonUtils.fromJson(message, CustomerRequest.class);
        Customer savedCustomer =  mapCustomerRequest(customer);
        customerService.addCustomer(savedCustomer);

    }

    @KafkaListener(topics = "update-customer-topic", groupId = "customer-group")
    public void updateCustomerConsume(String message) {
        System.out.println("Update Customer - Received Message: " + message);
        CustomerRequest customer = jsonUtils.fromJson(message, CustomerRequest.class);
        Customer savedCustomer = mapCustomerRequest(customer);
        customerService.updateCustomer(savedCustomer);
    }

    @KafkaListener(topics = "delete-customer-topic", groupId = "customer-group")
    public void deleteCustomerConsume(String message) {
        System.out.println("Delete Customer - Received Message: " + message);
        CustomerRequest customer = jsonUtils.fromJson(message, CustomerRequest.class);
        Customer savedCustomer = mapCustomerRequest(customer);
        customerService.deleteCustomer(savedCustomer);
    }

    public Customer mapCustomerRequest(CustomerRequest customer){
        return new Customer(customer.getDocument(), customer.getFirstname(),
                customer.getLastname(), customer.getAddress(), customer.getPhone(), customer.getEmail());
    }
}
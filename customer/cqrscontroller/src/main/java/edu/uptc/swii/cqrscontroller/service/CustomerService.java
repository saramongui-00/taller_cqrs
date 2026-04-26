package edu.uptc.swii.cqrscontroller.service;

import edu.uptc.swii.cqrscontroller.dto.CustomerRequest;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Customer;
import edu.uptc.swii.cqrscontroller.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository CustomerRepository;
    private final CustomerEventProducer customerEventProducer;
    private final String ADD_CUSTOMER_TOPIC = "add-customer-topic";
    private final String UPDATE_CUSTOMER_TOPIC = "update-customer-topic";
    private final String DELETE_CUSTOMER_TOPIC = "delete-customer-topic";

    public CustomerService(CustomerRepository customerRepository,
                           CustomerEventProducer customerEventProducer) {
        CustomerRepository = customerRepository;
        this.customerEventProducer = customerEventProducer;
    }

    public Customer addCustomer(CustomerRequest customer){
        Customer savedCustomer = CustomerRepository.save(mapCustomerRequest(customer));
        customerEventProducer.sendMessage(ADD_CUSTOMER_TOPIC,customer);
        return savedCustomer;
    }

    public Customer updateCustomer(CustomerRequest customer){
        Customer savedCustomer = CustomerRepository.save(mapCustomerRequest(customer));
        customerEventProducer.sendMessage(UPDATE_CUSTOMER_TOPIC,customer);
        return savedCustomer;
    }

    public String deleteCustomer(CustomerRequest customer){
        String message=new String();
        try{
            Customer savedCustomer = mapCustomerRequest(customer);
            CustomerRepository.delete(savedCustomer);
            customerEventProducer.sendMessage(DELETE_CUSTOMER_TOPIC,customer);
            message = "Customer deleted: "+customer.toString();
        }catch(Exception e){
            message = "Customer can't be deleted!";
            System.err.println(e.getMessage());
        }
        return message;
    }

    public Customer mapCustomerRequest(CustomerRequest customer){
        return new Customer(customer.getDocument(), customer.getFirstname(),
                customer.getLastname(), customer.getAddress(), customer.getPhone(), customer.getEmail());
    }
}

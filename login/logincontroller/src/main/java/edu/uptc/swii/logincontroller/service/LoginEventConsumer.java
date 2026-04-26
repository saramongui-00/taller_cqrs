package edu.uptc.swii.logincontroller.service;

import edu.uptc.swii.logincontroller.dto.CustomerRequest;
import edu.uptc.swii.logincontroller.model.Login;
import edu.uptc.swii.logincontroller.repository.LoginRepository;
import edu.uptc.swii.logincontroller.utils.JsonUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginEventConsumer {

    private final LoginRepository loginRepository;
    private final JsonUtils jsonUtils;
    private final LoginService loginService;

    public LoginEventConsumer(LoginRepository loginRepository,
                              JsonUtils jsonUtils, LoginService loginService) {
        this.loginRepository = loginRepository;
        this.jsonUtils = jsonUtils;
        this.loginService = loginService;
    }

    @KafkaListener(topics = "add-customer-topic")
    public void handleAddCustomerEvents(String customerJson){
        CustomerRequest customer = jsonUtils.fromJson(customerJson, CustomerRequest.class);

        Optional<Login> existing = loginRepository.findByDocument(customer.getDocument());
        if(existing.isPresent()){
            System.out.println("El login para el cliente " + customer.getDocument() + " ya existe");
            return;
        }

        Login login = new Login(customer.getDocument(), customer.getPassword());
        loginRepository.save(login);
        loginService.addLogin(login);

        System.out.println("Login creado automáticamente para el cliente " + customer.getDocument());
    }

    @KafkaListener(topics = "update-customer-topic")
    public void updateCustomerConsume(String customerJson) {
        CustomerRequest customer = jsonUtils.fromJson(customerJson, CustomerRequest.class);

        Optional<Login> existing = loginRepository.findByDocument(customer.getDocument());
        if(existing.isEmpty()){
            System.out.println("No existe login para el cliente " + customer.getDocument());
            return;
        }

        Login login = existing.get();
        login.setPassword(customer.getPassword());
        loginRepository.save(login);
        loginService.updateLogin(login);
        System.out.println("Login modificado automáticamente para el cliente " + customer.getDocument());
    }

    @KafkaListener(topics = "delete-customer-topic")
    public void deleteCustomerConsume(String customerJson) {
        CustomerRequest customer = jsonUtils.fromJson(customerJson, CustomerRequest.class);

        Optional<Login> existing = loginRepository.findByDocument(customer.getDocument());
        if(existing.isEmpty()){
            System.out.println("No existe login para el cliente " + customer.getDocument());
            return;
        }

        loginRepository.delete(existing.get());
        loginService.deleteLogin(existing.get());
        System.out.println("Login eliminado para el cliente " + customer.getDocument());
    }
}
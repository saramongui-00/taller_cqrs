package edu.uptc.swii.logincontroller.service;

import edu.uptc.swii.logincontroller.dto.CustomerRequest;
import edu.uptc.swii.logincontroller.model.Login;
import edu.uptc.swii.logincontroller.utils.JsonUtils;
import org.springframework.kafka.annotation.KafkaListener;

public class LoginEventConsumer {

    private final LoginService loginService;
    private final JsonUtils jsonUtils;

    public LoginEventConsumer(LoginService loginService,
                              JsonUtils jsonUtils){
        this.loginService = loginService;
        this.jsonUtils = jsonUtils;
    }

    @KafkaListener(topics = "add-customer-topic", groupId = "customer-group")
    public void handleAddCustomerEvents(String customerJson){
        CustomerRequest customer = jsonUtils.fromJson(customerJson, CustomerRequest.class);

        Login existing = loginService.findByCustomerId(customer.getDocument());
        if(existing != null){
            return;
        }

        Login login = new Login();
        login.setDocument(customer.getDocument());
        login.setPassword(customer.getPassword());

        loginService.addLogin(login);

        System.out.println("Login creado automáticamente para el cliente "
                + customer.getDocument());
    }

}

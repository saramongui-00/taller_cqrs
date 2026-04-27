package edu.uptc.swii.loginquery.service;

import edu.uptc.swii.loginquery.dto.CustomerRequest;
import edu.uptc.swii.loginquery.model.Login;
import edu.uptc.swii.loginquery.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoginEventConsumer {
    @Autowired
    private LoginService loginService;
    private JsonUtils jsonUtils;

    @KafkaListener(topics = "add-login-topic")
    public void addLoginConsume(String message) {
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.addLogin(savedLogin);
    }

    @KafkaListener(topics = "update-login-topic")
    public void updateLoginConsume(String message) {
        System.out.println("Update Login - Received Message: " + message);
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.updateLogin(savedLogin);
    }

    @KafkaListener(topics = "delete-login-topic")
    public void deleteLoginConsume(String message) {
        System.out.println("Delete Login - Received Message: " + message);
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.deleteLogin(savedLogin);
    }
}

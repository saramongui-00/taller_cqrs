package edu.uptc.swii.loginquery.service;

import edu.uptc.swii.loginquery.model.Login;
import edu.uptc.swii.loginquery.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoginEventConsumer {
    @Autowired
    private LoginService loginService;

    @KafkaListener(topics = "add-login-topic", groupId = "login-group")
    public void addLoginConsume(String message) {
        message = message.replace("\\", "");
        message = message.substring(1, message.length() - 1);
        System.out.println("Add Login - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.addLogin(savedLogin);

    }

    @KafkaListener(topics = "update-login-topic", groupId = "login-group")
    public void updateLoginConsume(String message) {
        System.out.println("Update Login - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.updateLogin(savedLogin);
    }

    @KafkaListener(topics = "delete-login-topic", groupId = "login-group")
    public void deleteLoginConsume(String message) {
        System.out.println("Delete Login - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Login savedLogin = jsonUtils.fromJson(message, Login.class);
        loginService.deleteLogin(savedLogin);
    }
}

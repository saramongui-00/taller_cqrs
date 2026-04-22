package edu.uptc.swii.logincontroller.controller;

import edu.uptc.swii.logincontroller.model.Login;
import edu.uptc.swii.logincontroller.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/addLogin")
    public String sendMessageAddLogin(@RequestBody Login Login) {
        loginService.addLogin(Login);
        return Login.toString();
    }

    @PostMapping("/updateLogin")
    public String sendMessageUpdateLogin(@RequestBody Login Login) {
        loginService.updateLogin(Login);
        return Login.toString();
    }

    @PostMapping("/delLogin")
    public String sendMessageDeleteLogin(@RequestBody Login Login) {
        return loginService.deleteLogin(Login);
    }
}

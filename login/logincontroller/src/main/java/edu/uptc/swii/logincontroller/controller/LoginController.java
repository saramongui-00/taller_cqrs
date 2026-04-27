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

    @PostMapping("/login")
    public String login(@RequestBody Login login){
        String msg = "Credenciales incorrectas";
        if(loginService.auth(login.getDocument(), login.getPassword())){
            msg = "Inicio de sesión correcto";
        }
        return msg;
    }

    @PostMapping("/addlogin")
    public String sendMessageAddCustomer(@RequestBody Login login) {
        loginService.addLogin(login);
        return login.toString();
    }

    @PostMapping("/updatelogin")
    public String sendMessageUpdateCustomer(@RequestBody Login login) {
        loginService.updateLogin(login);
        return login.toString();
    }

    @PostMapping("/dellogin")
    public String sendMessageDeleteCustomer(@RequestBody Login login) {
        loginService.deleteLogin(login);
        return login.toString();
    }
}

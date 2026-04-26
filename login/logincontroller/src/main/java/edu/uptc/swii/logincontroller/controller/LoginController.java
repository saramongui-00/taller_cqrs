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
}

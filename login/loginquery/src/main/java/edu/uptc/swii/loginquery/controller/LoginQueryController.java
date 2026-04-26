package edu.uptc.swii.loginquery.controller;

import edu.uptc.swii.loginquery.model.Login;
import edu.uptc.swii.loginquery.repository.LoginRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginQueryController {
    private final LoginRepository loginRepository;

    public LoginQueryController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/allLogins")
    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }


}

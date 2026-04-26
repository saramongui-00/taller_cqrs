package edu.uptc.swii.logincontroller.service;

import edu.uptc.swii.logincontroller.model.Login;
import edu.uptc.swii.logincontroller.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginEventProducer loginEventProducer;
    private final String ADD_LOGIN_TOPIC = "add-login-topic";
    private final String UPDATE_LOGIN_TOPIC = "update-login-topic";
    private final String DELETE_LOGIN_TOPIC = "delete-login-topic";

    public LoginService(LoginRepository loginRepository, LoginEventProducer loginEventProducer){
        this.loginRepository = loginRepository;
        this.loginEventProducer = loginEventProducer;
    }

    public void addLogin(Login login){
        loginEventProducer.sendMessage(ADD_LOGIN_TOPIC, login);
    }

    public void updateLogin(Login login){
        loginEventProducer.sendMessage(UPDATE_LOGIN_TOPIC, login);
    }

    public void deleteLogin(Login login){
        loginEventProducer.sendMessage(DELETE_LOGIN_TOPIC, login);
    }

    public boolean auth(String customerid,String password){
        Login login = findByCustomerId(customerid);

        if(login == null){
            return false;
        }

        if(login.getPassword().equals(password)){
            return true;
        }

        return false;
    }

    public Login findByCustomerId(String customerid){
        Optional<Login> optional = loginRepository.findByDocument(customerid);
        return optional.orElse(null);
    }
}

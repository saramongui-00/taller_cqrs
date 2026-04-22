package edu.uptc.swii.logincontroller.service;

import edu.uptc.swii.logincontroller.model.Login;
import edu.uptc.swii.logincontroller.repository.LoginRepository;

import java.util.Optional;

public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginEventProducer loginEventProducer;
    private final LoginEventConsumer loginEventConsumer;
    private final String ADD_LOGIN_TOPIC = "add-login-topic";
    private final String UPDATE_LOGIN_TOPIC = "update-login-topic";
    private final String DELETE_LOGIN_TOPIC = "delete-login-topic";

    public LoginService(LoginRepository loginRepository, LoginEventProducer loginEventProducer,
                        LoginEventConsumer loginEventConsumer){
        this.loginRepository = loginRepository;
        this.loginEventProducer = loginEventProducer;
        this.loginEventConsumer = loginEventConsumer;
    }

    public Login addLogin(Login login){
        loginRepository.save(login);
        loginEventProducer.sendMessage(ADD_LOGIN_TOPIC, login);
        return loginRepository.save(login);
    }

    public Login updateLogin(Login login){
        loginRepository.save(login);
        loginEventProducer.sendMessage(UPDATE_LOGIN_TOPIC, login);
        return loginRepository.save(login);
    }

    public String deleteLogin(Login login){
        String message = "";
        try {
            loginRepository.delete(login);
            loginEventProducer.sendMessage(DELETE_LOGIN_TOPIC, login);
            message = "Login deleted: "+ login.toString();
        }catch(Exception e){
            message = "Login can't be deleted!";
            System.err.println(e.getMessage());
        }
        return message;
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
        Optional<Login> optional = loginRepository.findByCustomerid(customerid);
        return optional.orElse(null);
    }
}

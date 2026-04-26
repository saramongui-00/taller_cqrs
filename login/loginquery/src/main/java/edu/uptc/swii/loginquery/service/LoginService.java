package edu.uptc.swii.loginquery.service;

import edu.uptc.swii.loginquery.model.Login;
import edu.uptc.swii.loginquery.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
   @Autowired
   private final LoginRepository loginRepository;

   public LoginService(LoginRepository loginRepository){
       this.loginRepository = loginRepository;
   }

   public void addLogin(Login login){
       loginRepository.insert(login);
   }

    public void updateLogin(Login login){
        loginRepository.save(login);
    }

    public void deleteLogin(Login login){
        loginRepository.delete(login);
    }

    public List<Login>getAllLogins(){
       return loginRepository.findAll();
    }

    public Login getLoginById(long id){
       Optional<Login> optLogin = loginRepository.findById(id);
       return optLogin.get();
    }
}

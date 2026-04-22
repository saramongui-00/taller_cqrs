package edu.uptc.swii.logincontroller.repository;

import edu.uptc.swii.logincontroller.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByCustomerid(String customerid);

}

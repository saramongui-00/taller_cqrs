package edu.uptc.swii.loginquery.repository;

import edu.uptc.swii.loginquery.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<Login, Long> {
}

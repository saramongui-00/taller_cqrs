package edu.uptc.swii.loginquery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="login")
public class Login {
    @Id
    @JsonProperty("id")
    private long id;
    @JsonProperty("document")
    private String document;
    @JsonProperty("password")
    private String password;

    public Login(){

    }

    public Login(long id, String document, String password) {
        this.id = id;
        this.document = document;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

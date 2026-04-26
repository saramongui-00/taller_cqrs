package edu.uptc.swii.logincontroller.model;

import jakarta.persistence.*;

@Entity
@Table(name="`login`")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "document")
    private String document;
    @Column(name = "password")
    private String password;

    public Login(){

    }

    public Login(String document, String password) {
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

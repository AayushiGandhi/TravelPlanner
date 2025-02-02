package com.example.emailservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "emaildb")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Email(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Email() {
    }
}

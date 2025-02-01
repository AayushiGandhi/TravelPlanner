package com.example.emailservice.service;

import com.example.emailservice.model.Email;
import com.example.emailservice.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public List<Email> getAllTrips() {
        return emailRepository.findAll();
    }

    public Email getTripById(Long id) {
        return emailRepository.findById(id).orElse(null);
    }

    public Email saveTrip(Email email) {
        return emailRepository.save(email);
    }

    public void deleteTrip(Long id) {
        emailRepository.deleteById(id);
    }
}

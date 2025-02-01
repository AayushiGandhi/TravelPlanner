package com.example.emailservice.kafka;

import com.example.emailservice.model.Email;
import com.example.emailservice.service.EmailService;
import com.example.travel.dto.TripEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class TravelConsumer {
    @Autowired
    private EmailService emailService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(TripEvent event) {
        String message = "Your trip is successfully booked with following details: "
                            + event.getStatus() + event.getMessage() + event.getTrip().getDestination();
        System.out.println(message);

        Email email = new Email();
        email.setMessage(message);

        //send email to customer

        //Save in database
        saveToEmailDB(email);
    }

    public void saveToEmailDB(Email email) {
        emailService.saveTrip(email);
    }
}


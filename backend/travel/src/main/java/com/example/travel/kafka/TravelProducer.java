package com.example.travel.kafka;

import com.example.travel.dto.TripEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.travel.dto.Trip;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

//Creating order producer as a springboot class
@Service
public class TravelProducer {

    private NewTopic topic;

    private KafkaTemplate<String, TripEvent> kafkaTemplate;

    //Used spring provided constructor for dependency injection to inject new topic in kafka template classes
    public TravelProducer(NewTopic topic, KafkaTemplate<String, TripEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Trip trip) {
        TripEvent event = new TripEvent();
        event.setStatus("RECEIVED");
        event.setMessage("New trip details");
        event.setTrip(trip);

        System.out.println("Trip event received in email service : {\"ID\":\"" + event.getStatus() +
                "\", \"Destination\":\"" + event.getMessage() +
                "\", \"Start date\":" + event.getTrip().getDestination() + "}");

        //Send the message to kafka topic using kafka template
        Message<TripEvent> message = MessageBuilder
                .withPayload(event) //pass payload as event
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build(); //pass topic name in message in header

        kafkaTemplate.send(message);
    }
}

package com.employee.services;

import jakarta.annotation.PostConstruct;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EmployeeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        System.out.println("Sending : " + message);
        kafkaTemplate.send("employee-events", message);
        System.out.println("Notification Sent : " + message);
    }

    @PostConstruct
    public void init() {
        System.out.println("EmployeeProducer Loaded");
        System.out.println(kafkaTemplate);
    }
}


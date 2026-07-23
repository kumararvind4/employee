package com.employee.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private final EmailService emailService;

    public EmployeeConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "employee-events",
            groupId = "employee-group")
    public void consume(String message) {

        emailService.sendNotification(
                "kumar.arvind4@hcltech.com");

        System.out.println(message);
    }
}

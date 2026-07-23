package com.employee.controllers;

import com.employee.services.EmployeeProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final EmployeeProducer producer;

    public KafkaController(EmployeeProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public String publish(@RequestParam String message) {

        producer.sendMessage(message);

        return "Message Sent Successfully";
    }
}
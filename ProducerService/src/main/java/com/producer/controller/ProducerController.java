package com.producer.controller;

import com.producer.model.Car;
import com.producer.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProducerController {

    @Autowired
    public ProducerController(Sender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    private final Sender rabbitMQSender;

    @Value("${app.message}")
    private String message;

    @PostMapping(value = "message")
    public String publishMessage(@RequestBody Car car) {
        rabbitMQSender.send(car);
        return this.message;
    }
}

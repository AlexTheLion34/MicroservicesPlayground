package com.producer.service.impl;

import com.producer.model.Message;
import com.producer.service.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender implements Sender {

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.queue}")
    private String queue;

    @Override
    public void send(Message message) {
       rabbitTemplate.convertAndSend(queue, message);
    }
}

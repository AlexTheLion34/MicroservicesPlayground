package com.producer.service.impl;

import com.producer.model.Message;
import com.producer.service.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RabbitMQSender implements Sender {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.exchange}")
    private String exchange;

    @Override
    public void send(Message message) {
        if (counter.get() == 0) {
            rabbitTemplate.convertAndSend(exchange, "zero", message);
        } else if (counter.get() % 2 != 0) {
            rabbitTemplate.convertAndSend(exchange, "not-even", message);
        } else {
            rabbitTemplate.convertAndSend(exchange, "even", message);
        }
        counter.incrementAndGet();
    }
}

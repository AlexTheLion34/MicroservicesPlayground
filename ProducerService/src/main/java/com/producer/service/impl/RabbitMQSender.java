package com.producer.service.impl;

import com.producer.model.Message;
import com.producer.service.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender implements Sender {

    private final static Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.queue}")
    private String queue;

    @Override
    public void send(Message message) {
       String response = (String) rabbitTemplate.convertSendAndReceive(queue, message);
       logger.info("Consumer: " + response);
    }
}

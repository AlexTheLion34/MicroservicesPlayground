package com.consumer.service.impl;

import com.consumer.model.Message;
import com.consumer.service.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RabbitMQReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    private static final Random random = new Random();

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessageAsFirstListener(Message message) throws InterruptedException {
        logger.info("First listener received message from queue: " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessageAsFirstSecond(Message message) throws InterruptedException {
        logger.info("Second listener received message from queue: " + message);
        Thread.sleep(100 * random.nextInt(20));
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {}
}

package com.consumer.service.impl;

import com.consumer.model.Car;
import com.consumer.service.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver implements MessageReceiver, RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);


    @RabbitListener(queues = "${spring.rabbitmq.queue1}")
    public void receiveMessageFromFirstQueue(Car car) {
        logger.info("Received message from queue 1: " + car);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void receiveMessageFromSecondQueue(Car car) {
        logger.info("Received message from queue 2: " + car);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {}
}

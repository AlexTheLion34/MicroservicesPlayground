package com.producer.service.impl;

import com.producer.model.Car;
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

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    /*
     * pattern: <speed>.<gears>.<brand>
     */
    @Override
    public void send(Car car) {
        System.out.println(car);
        String key = car.getSpeed() + "." + car.getGear() + "." + car.getBrand();
        System.out.println(key);
        rabbitTemplate.convertAndSend(exchange, key, car);
    }
}

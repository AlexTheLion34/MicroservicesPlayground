package com.producer.service;

import com.producer.model.Message;

public interface Sender {
    void send(Message message);
}

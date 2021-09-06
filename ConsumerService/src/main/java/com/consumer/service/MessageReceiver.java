package com.consumer.service;

import com.consumer.model.Message;

public interface MessageReceiver {
    void receiveMessage(Message message);
}

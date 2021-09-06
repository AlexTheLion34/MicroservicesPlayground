package com.consumer.service;

import com.consumer.model.Message;

public interface MessageReceiver {
    String receiveMessage(Message message);
}

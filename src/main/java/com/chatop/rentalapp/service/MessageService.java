package com.chatop.rentalapp.service;

import com.chatop.rentalapp.model.Message;

import java.util.List;

public interface MessageService {
    Message create(Message message);

    List<Message> findAll();
}

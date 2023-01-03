package com.chatop.rentalapp.service.implementation;

import com.chatop.rentalapp.model.Message;
import com.chatop.rentalapp.repository.MessageRepository;
import com.chatop.rentalapp.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }
}

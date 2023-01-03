package com.chatop.rentalapp.mapper;

import com.chatop.rentalapp.dto.response.MessageResponse;
import com.chatop.rentalapp.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper {
    public List<MessageResponse> toDtoList(List<Message> entities) {
        return entities.stream().map(MessageResponse::new).toList();
    }
}
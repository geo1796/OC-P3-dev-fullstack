package com.chatop.rentalapp.mapper;

import com.chatop.rentalapp.dto.response.MessageDto;
import com.chatop.rentalapp.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageMapper {
    public List<MessageDto> toDtoList(List<Message> entities) {
        return entities.stream().map(MessageDto::new).toList();
    }
}
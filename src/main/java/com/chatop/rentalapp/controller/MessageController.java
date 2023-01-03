package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.request.MessageRequest;
import com.chatop.rentalapp.dto.response.MessageResponse;
import com.chatop.rentalapp.mapper.MessageMapper;
import com.chatop.rentalapp.model.Message;
import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.model.User;
import com.chatop.rentalapp.service.MessageService;
import com.chatop.rentalapp.service.MyUserDetailsService;
import com.chatop.rentalapp.service.RentalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;
    private RentalService rentalService;
    private MyUserDetailsService userDetailsService;
    private MessageMapper messageMapper;

    @PostMapping
    ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequest messageRequest) {
        Optional<User> optionalUser = userDetailsService.findById(messageRequest.getUserId());
        Optional<Rental> optionalRental = rentalService.findById(messageRequest.getRentalId());

        if (optionalRental.isEmpty()) {
            return new ResponseEntity<>(
                    Map.of("message", String.format("rental {id=%s} not found", messageRequest.getRentalId())),
                    HttpStatus.NOT_FOUND
            );
        }

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(
                    Map.of("message", String.format("user {id=%s} not found", messageRequest.getUserId())),
                    HttpStatus.NOT_FOUND
            );
        }

        messageService.create(Message.builder()
                .message(messageRequest.getMessage())
                .user(optionalUser.get())
                .rental(optionalRental.get())
                .build());

        return new ResponseEntity<>(Map.of("message", "Message sent with success"), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<MessageResponse>> getAll() {
        List<MessageResponse> messages = messageMapper.toDtoList(messageService.findAll());
        return ResponseEntity.ok(messages);
    }
}

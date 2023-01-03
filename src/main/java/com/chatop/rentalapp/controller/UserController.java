package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.mapper.UserMapper;
import com.chatop.rentalapp.model.User;
import com.chatop.rentalapp.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private MyUserDetailsService userDetailsService;
    private UserMapper userMapper;

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable int id) {
        Optional<User> optionalUser = userDetailsService.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(
                    Map.of("message", String.format("user {id=%s} not found", id)),
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(userMapper.toDto(optionalUser.get()));
    }
}

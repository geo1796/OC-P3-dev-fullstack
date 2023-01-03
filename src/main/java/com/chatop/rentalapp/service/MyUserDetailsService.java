package com.chatop.rentalapp.service;

import com.chatop.rentalapp.dto.request.RegisterRequest;
import com.chatop.rentalapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface MyUserDetailsService extends UserDetailsService {
    User getByEmail(String email);
    Optional<User> findByEmail(String email);
    User register(RegisterRequest registerRequest);
}

package com.chatop.rentalapp.service;

import com.chatop.rentalapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MyUserDetailsService extends UserDetailsService {
    User getByEmail(String email);
}

package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.request.RegisterRequest;
import com.chatop.rentalapp.dto.request.LoginRequest;
import com.chatop.rentalapp.dto.response.LoginResponse;
import com.chatop.rentalapp.model.User;
import com.chatop.rentalapp.service.MyUserDetailsService;
import com.chatop.rentalapp.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private MyUserDetailsService userDetailsService;
    private DaoAuthenticationProvider authenticationProvider;
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String jwt = jwtUtil.generateTokenFromEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        Optional<User> optionalUser = userDetailsService.findByEmail(registerRequest.getEmail());
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User newUser = userDetailsService.register(registerRequest);
        String jwt = jwtUtil.generateTokenFromEmail(newUser.getEmail());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}

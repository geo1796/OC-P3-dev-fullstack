package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.mapper.RentalMapper;
import com.chatop.rentalapp.service.MyUserDetailsService;
import com.chatop.rentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private RentalService rentalService;
    private RentalMapper rentalMapper;
    private MyUserDetailsService userDetailsService;

    @GetMapping
    ResponseEntity<?> getAll() {
        List<RentalResponse> rentals = rentalMapper.toDtoList(rentalService.findAll());
        return ResponseEntity.ok(Map.of("rentals", rentals));
    }
}

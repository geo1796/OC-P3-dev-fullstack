package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.mapper.RentalMapper;
import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.service.MyUserDetailsService;
import com.chatop.rentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Rental> optionalRental = rentalService.findById(id);
        if (optionalRental.isEmpty()) {
            return new ResponseEntity<>(
                    Map.of("message", String.format("rental {id=%s} not found", id)),
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(new RentalResponse(optionalRental.get()));
    }
}

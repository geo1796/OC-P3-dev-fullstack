package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.mapper.RentalMapper;
import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.model.User;
import com.chatop.rentalapp.service.MyUserDetailsService;
import com.chatop.rentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> create(@RequestParam("name") String name,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("description") String description,
                             @RequestParam("surface") BigDecimal surface,
                             @RequestPart("picture") MultipartFile picture) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        rentalService.save(Rental.builder()
                .name(name)
                .price(price)
                .picture("https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
                .description(description)
                .surface(surface)
                .owner(owner)
                .build());

        return ResponseEntity.ok(Map.of("message", "Rental created !"));
    }
}

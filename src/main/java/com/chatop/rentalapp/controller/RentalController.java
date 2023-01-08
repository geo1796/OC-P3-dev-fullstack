package com.chatop.rentalapp.controller;

import com.chatop.rentalapp.dto.response.MessageResponse;
import com.chatop.rentalapp.dto.response.RentalDto;
import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.mapper.RentalMapper;
import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private RentalService rentalService;
    private RentalMapper rentalMapper;

    @GetMapping
    ResponseEntity<RentalResponse> getAll() {
        RentalResponse rentals = rentalMapper.toDto(rentalService.findAll());
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    ResponseEntity<RentalDto> getById(@PathVariable int id) {
        Optional<Rental> optionalRental = rentalService.findById(id);
        if (optionalRental.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(new RentalDto(optionalRental.get()));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<MessageResponse> create(@RequestParam("name") String name,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("description") String description,
                             @RequestParam("surface") BigDecimal surface,
                             @RequestPart("picture") MultipartFile picture) throws IOException {
        rentalService.create(name, description, price, surface, picture);

        return ResponseEntity.ok(new MessageResponse("Rental created !"));
    }

    @PutMapping("/{id}")
    ResponseEntity<MessageResponse> update(@RequestParam("name") String name,
                                           @RequestParam("price") BigDecimal price,
                                           @RequestParam("description") String description,
                                           @RequestParam("surface") BigDecimal surface,
                                           @PathVariable int id) {
        Optional<Rental> optionalRental = rentalService.findById(id);
        if (optionalRental.isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse(String.format("rental {id=%s} not found", id)),
                    HttpStatus.NOT_FOUND
            );
        }

        Rental entity = optionalRental.get();
        entity.setName(name);
        entity.setPrice(price);
        entity.setDescription(description);
        entity.setSurface(surface);
        rentalService.save(entity);
        return ResponseEntity.ok(new MessageResponse("Rental updated !"));
    }
}

package com.chatop.rentalapp.service;

import com.chatop.rentalapp.model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<Rental> findAll();
    Optional<Rental> findById(int id);
}

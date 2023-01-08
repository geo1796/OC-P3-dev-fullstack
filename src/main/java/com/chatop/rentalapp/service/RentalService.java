package com.chatop.rentalapp.service;

import com.chatop.rentalapp.model.Rental;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<Rental> findAll();
    Optional<Rental> findById(int id);
    Rental save(Rental rental);

    Rental create(String name, String description, BigDecimal price, BigDecimal surface, MultipartFile picture) throws IOException;
}

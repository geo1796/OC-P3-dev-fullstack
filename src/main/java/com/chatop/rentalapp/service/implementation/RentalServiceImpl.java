package com.chatop.rentalapp.service.implementation;

import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.repository.RentalRepository;
import com.chatop.rentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    @Override
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Optional<Rental> findById(int id) {
        return rentalRepository.findById(id);
    }

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}

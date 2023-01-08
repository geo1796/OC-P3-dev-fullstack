package com.chatop.rentalapp.service.implementation;

import com.chatop.rentalapp.model.Rental;
import com.chatop.rentalapp.model.User;
import com.chatop.rentalapp.repository.RentalRepository;
import com.chatop.rentalapp.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Value("${pictures.path}")
    private String picturesPath;
    @Value("${pictures.server-url}")
    private String picturesServerUrl;

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

    @Override
    public Rental create(String name, String description, BigDecimal price, BigDecimal surface, MultipartFile picture) throws IOException {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        picture.transferTo(Paths.get(picturesPath).resolve(picture.getOriginalFilename()));
        return save(Rental.builder()
                .owner(owner)
                .name(name)
                .description(description)
                .picture(picturesServerUrl + picture.getOriginalFilename())
                .surface(surface)
                .price(price)
                .build());
    }
}

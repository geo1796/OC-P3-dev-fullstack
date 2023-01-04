package com.chatop.rentalapp.mapper;

import com.chatop.rentalapp.dto.response.RentalDto;
import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.model.Rental;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalMapper {
    public RentalResponse toDto(List<Rental> entities) {
        return new RentalResponse(entities.stream().map(RentalDto::new).toList());
    }
}

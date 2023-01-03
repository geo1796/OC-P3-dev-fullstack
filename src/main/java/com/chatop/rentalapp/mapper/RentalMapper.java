package com.chatop.rentalapp.mapper;

import com.chatop.rentalapp.dto.response.RentalResponse;
import com.chatop.rentalapp.model.Rental;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalMapper {
    public List<RentalResponse> toDtoList(List<Rental> entities) {
        return entities.stream().map(RentalResponse::new).toList();
    }
}

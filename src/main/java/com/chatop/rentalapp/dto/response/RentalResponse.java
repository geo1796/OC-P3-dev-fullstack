package com.chatop.rentalapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RentalResponse {
    List<RentalDto> rentals;
}

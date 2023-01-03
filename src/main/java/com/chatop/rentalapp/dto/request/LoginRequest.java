package com.chatop.rentalapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

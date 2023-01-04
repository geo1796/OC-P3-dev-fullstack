package com.chatop.rentalapp.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

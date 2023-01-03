package com.chatop.rentalapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotBlank
    private String message;
    @JsonProperty(value = "rental_id")
    @NotNull
    private Integer rentalId;
    @JsonProperty(value = "user_id")
    @NotNull
    private Integer userId;
}

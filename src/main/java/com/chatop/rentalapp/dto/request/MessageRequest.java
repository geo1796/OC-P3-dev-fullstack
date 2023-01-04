package com.chatop.rentalapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

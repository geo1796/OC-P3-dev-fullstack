package com.chatop.rentalapp.dto.response;

import com.chatop.rentalapp.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class MessageDto {
    @NotBlank
    private String message;
    @JsonProperty(value = "rental_id")
    private Integer rentalId;
    @JsonProperty(value = "user_id")
    private Integer userId;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    private Date updatedAt;

    public MessageDto(Message entity) {
        this.message = entity.getMessage();
        this.rentalId = entity.getRental().getId();
        this.userId = entity.getUser().getId();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}

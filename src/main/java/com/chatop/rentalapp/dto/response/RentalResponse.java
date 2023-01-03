package com.chatop.rentalapp.dto.response;

import com.chatop.rentalapp.model.Rental;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class RentalResponse {
    private int id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    private String description;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    private Date updatedAt;
    @JsonProperty(value = "owner_id")
    private int ownerId;

    public RentalResponse(Rental entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surface = entity.getSurface();
        this.price = entity.getPrice();
        this.picture = entity.getPicture();
        this.description = entity.getDescription();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.ownerId = entity.getOwner().getId();
    }
}

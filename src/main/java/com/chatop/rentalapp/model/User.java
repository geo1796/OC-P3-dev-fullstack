package com.chatop.rentalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @JsonProperty(value = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}

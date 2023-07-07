package com.example.projectuzummarket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RegisterDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    @Column(unique = true)
    private String username;
    @Email
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isActive;
    private boolean isConsumer;

}

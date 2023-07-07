package com.example.projectuzummarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String username;
    private String password;

}

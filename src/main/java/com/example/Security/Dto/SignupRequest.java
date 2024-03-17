package com.example.Security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean gender;
    private String image;
    private Boolean status;
    private String token;
    private Set<String> role;
}

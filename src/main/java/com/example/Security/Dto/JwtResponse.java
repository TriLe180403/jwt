package com.example.Security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean gender;
    private String image;
    private LocalDate registerDate;
    private List<String> roles;


    public JwtResponse(String jwt, int id, String name, String email, String password, String phone, String address, Boolean gender, String image, List<String> roles) {
    }
}

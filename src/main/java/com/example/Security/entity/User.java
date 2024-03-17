package com.example.Security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Boolean gender;
    private Boolean status;
    private String image;
    private String token;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRole> roles = new HashSet<>();


    public User(String name, String email, String encode, String phone, String address, String image, Boolean gender, String s) {
    }

    public User(String name, String email, String encode, String phone, String address, Boolean gender, Boolean status, String image, String s) {
    }

    public Object getUserId() {
        return null;
    }

    public Object getRegisterDate() {
        return null;
    }
}

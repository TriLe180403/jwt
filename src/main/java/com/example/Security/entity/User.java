package com.example.Security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

package com.example.Security.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsService implements UserDetails {
    private static final int serialVersionUID = 1;
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean gender;
    private String image;
    @JsonIgnore
    private String password;


    public Collection<? extends GrantedAuthority> Authorities;






    @Override
//    getAuthorities(): trả về danh sách các quyền của người dùng
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
//    getPassword(): trả về password đã dùng trong qúa trình xác thực
    public String getPassword() {
        return null;
    }

    @Override
//    getUsername(): trả về username đã dùng trong qúa trình xác thực
    public String getUsername() {
        return null;
    }

    @Override
//    isAccountNonExpired(): trả về true nếu tài khoản của người dùng chưa hết hạn
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
//    isAccountNonLocked(): trả về true nếu người dùng chưa bị khóa
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
//    isCredentialsNonExpired(): trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
//    isEnabled(): trả về true nếu người dùng đã được kích hoạt
    public boolean isEnabled() {
        return false;
    }
}

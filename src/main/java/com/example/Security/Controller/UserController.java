package com.example.Security.Controller;

import com.example.Security.Dto.JwtResponse;
import com.example.Security.Dto.LoginRequest;
import com.example.Security.Dto.MessageResponse;
import com.example.Security.Dto.SignupRequest;
import com.example.Security.Repository.AppRoleRepository;
import com.example.Security.Repository.CartRepository;
import com.example.Security.Repository.UserRepository;
import com.example.Security.Security.JwtUtils;
import com.example.Security.Service.UserDetailsService;
import com.example.Security.entity.AppRole;
import com.example.Security.entity.Cart;
import com.example.Security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){

        return ResponseEntity.ok(userRepository.findByStatusTrue());
    }
    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Integer id){
        if (!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userRepository.findById(id).get());
    }
    @GetMapping("email/{email}")
    public ResponseEntity<List<User>> getOneByEmail(@PathVariable("email") String email){
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.ok(Collections.singletonList(userRepository.findByEmail(email)));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public  ResponseEntity<User> post(@RequestBody User user){
        if (userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.notFound().build();
        }
        if (userRepository.existsById(user.getId())){
            return ResponseEntity.badRequest().build();
        }

        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(1,null));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtUtils.doGenerateToken(user.getEmail()));
        User u = userRepository.save(user);
        Cart c = new Cart(0,0.0,u.getAddress(),u.getPhone());
        cartRepository.save(c);
        return ResponseEntity.ok(u);


    }

    @PutMapping("{id}")
    public ResponseEntity<User> put(@PathVariable("id") Integer id, @RequestBody User user){
        if (!userRepository.existsById(id)){
            return  ResponseEntity.notFound().build();
        }
        if (!id.equals(user.getId())){
            return ResponseEntity.badRequest().build();
        }
        User temp = userRepository.findById(id).get();
        if (!user.getPassword().equals(temp.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(1,null));
        user.setRoles(roles);
        return ResponseEntity.ok(userRepository.save(user));
    }
    @PutMapping("admin/{id}")
    public ResponseEntity<User> putAdmin(@PathVariable("id") Integer id, @RequestBody User user){
        if (!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        if (!id.equals(user.getId())){
            return ResponseEntity.badRequest().build();
        }
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(2, null));

        user.setRoles(roles);
        return ResponseEntity.ok(userRepository.save(user));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> detete(@PathVariable("id") Integer id){
        if (!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        User u = userRepository.findById(id).get();
        u.setStatus(false);
        userRepository.save(u);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsService userDetails = (UserDetailsService) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(),
                userDetails.getEmail(), userDetails.getPassword(), userDetails.getPhone(),userDetails.getAddress(),
                userDetails.getGender(), userDetails.getImage(), roles));
         }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest){
        if (userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("error: Email is already taken!"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("error: Email is already in use!"));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }




}

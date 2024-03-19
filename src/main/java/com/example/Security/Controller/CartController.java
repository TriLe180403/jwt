package com.example.Security.Controller;

import com.example.Security.Repository.CartDetailRepository;
import com.example.Security.Repository.CartRepository;
import com.example.Security.Repository.UserRepository;
import com.example.Security.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{email}")
    public ResponseEntity<Cart>  getCartUser(@PathVariable("email") String email){
        if (!userRepository.existsByEmail(email)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartRepository.findByUser(userRepository.findByEmail(email)));

    }
    @PutMapping("/user/{email}")
    public ResponseEntity<Cart> putCartUser(@PathVariable("email") String email, @RequestBody Cart cart){
        if (!userRepository.existsByEmail(email)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartRepository.save(cart));
    }
}

package com.example.Security.Repository;

import com.example.Security.entity.Cart;
import com.example.Security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}

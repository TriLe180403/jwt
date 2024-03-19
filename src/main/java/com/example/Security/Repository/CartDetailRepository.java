package com.example.Security.Repository;

import com.example.Security.entity.Cart;
import com.example.Security.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {


    static List<CartDetail> findByCart(Optional<Cart> cart) {

        return null;
    }
}

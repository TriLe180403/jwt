package com.example.Security.Controller;

import com.example.Security.Repository.CartDetailRepository;
import com.example.Security.Repository.CartRepository;
import com.example.Security.Repository.ProductRepository;
import com.example.Security.entity.CartDetail;
import com.example.Security.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cartDetail")
public class CartDetailController {
    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("cart/{id}")
    public ResponseEntity<List<CartDetail>> getByCartId(@PathVariable("id") Integer id){
        if (!cartRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CartDetailRepository.findByCart(Optional.of(cartRepository.findById(id).get())));
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<CartDetail> getOne(@PathVariable("id") Integer id){
        if (!cartDetailRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDetailRepository.findById(id).get());
    }
    @PostMapping()
    public ResponseEntity<CartDetail> post(@RequestBody CartDetail detail){
        if (!cartDetailRepository.existsById(detail.getCartId())) {
            return ResponseEntity.notFound().build();
        }
        boolean check = false;
        List<Product> listP = productRepository.findByStatusTrue();
        Product product = productRepository.findByProductIdAndStatusTrue(detail.getProductId());
        for (Product p : listP){
            if (p.getProductId() == product.getProductId()){
                check = true;
            }
        };
        if (!check){
            return ResponseEntity.notFound().build();
        }
        List<CartDetail> listD = CartDetailRepository
                .findByCart(cartRepository.findById(detail.getCart()));
        return null;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!cartDetailRepository.existsById(Math.toIntExact(id))) {
            return ResponseEntity.notFound().build();
        }
        cartDetailRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }


}

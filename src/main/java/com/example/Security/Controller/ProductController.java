package com.example.Security.Controller;

import com.example.Security.Repository.CategoryRepository;
import com.example.Security.Repository.ProductRepository;
import com.example.Security.entity.Category;
import com.example.Security.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    ProductRepository repo;

    @Autowired
    CategoryRepository cRepo;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(repo.findByStatusTrue());
    }
    @GetMapping("bestseller")
    public ResponseEntity<List<Product>> getBestSeller(){
        return ResponseEntity.ok(repo.findByStatusTrueOrderBySoldesc());
    }
    @GetMapping("bestseller-admin")
    public ResponseEntity<List<Product>> getBestSellerAdmin(){
        return ResponseEntity.ok(repo.findTo10ByOrderBySolDesc());
    }
    @GetMapping("latest")
    public ResponseEntity<List<Product>> getLasted(){
        return ResponseEntity.ok(repo.findByStatusTrueOrderByEnteredDateDesc());
    }
    @GetMapping("suggest/{categoryId}/{productId}")
    public ResponseEntity<List<Product>> suggest(@PathVariable("categoryId") Integer categoryId,
                                                 @PathVariable("productId") Integer productId){
        return ResponseEntity.ok(repo.findProductSuggest(categoryId, productId));
    }
    @GetMapping("category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") Integer id){
        if (!cRepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Category c = cRepo.findById(id).get();
        return ResponseEntity.ok(repo.findbyCategory(c));
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Integer id){
        if (repo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }
    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product){
        if (repo.existsById(product.getProductId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.save(product));
    }
    @PutMapping("{id}")
    public ResponseEntity<Product> put(@PathVariable("id") Integer id, @RequestBody Product product){
        if (!id.equals(product.getProductId())){
            return ResponseEntity.badRequest().build();
        }
        if (!repo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(product));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void > delete(@PathVariable("id") Integer id){
        if (!repo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Product p = repo.findById(id).get();
        p.setStatus(false);
        repo.save(p);
        return ResponseEntity.ok().build();
    }
}

package com.example.Security.Repository;

import com.example.Security.entity.Category;
import com.example.Security.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatusTrue();

    List<Product> findByStatusTrueOrderBySoldesc();

    List<Product> findTo10ByOrderBySolDesc();

    List<Product> findByStatusTrueOrderByEnteredDateDesc();

    List<Product> findProductSuggest(Integer categoryId, Integer productId);

    List<Product> findbyCategory(Category c);
}

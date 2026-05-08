 package com.ws101.senobiorayandayan.ecommerceapi.repository;

import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryNameIgnoreCase(String categoryName);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsInPriceRange(@Param("min") double min, @Param("max") double max);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}
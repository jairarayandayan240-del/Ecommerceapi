package com.ws101.senobiorayandayan.ecommerceapi.repository;

import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method naming query: find products by their category's name
    List<Product> findByCategoryNameIgnoreCase(String categoryName);

    // Custom JPQL query: find products within a price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsInPriceRange(@Param("min") double min, @Param("max") double max);

    // Search by product name containing a keyword (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
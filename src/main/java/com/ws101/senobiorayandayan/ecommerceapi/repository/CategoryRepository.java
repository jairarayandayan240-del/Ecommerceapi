package com.ws101.senobiorayandayan.ecommerceapi.repository;

import com.ws101.senobiorayandayan.ecommerceapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
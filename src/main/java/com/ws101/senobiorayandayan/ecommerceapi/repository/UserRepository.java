 package com.ws101.senobiorayandayan.ecommerceapi.repository;

import com.ws101.senobiorayandayan.ecommerceapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
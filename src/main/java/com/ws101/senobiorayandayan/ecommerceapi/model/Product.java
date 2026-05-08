 package com.ws101.senobiorayandayan.ecommerceapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int stockQuantity;

    private String imageUrl;

    public Product(String name, String description, double price,
                   Category category, int stockQuantity, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }
}
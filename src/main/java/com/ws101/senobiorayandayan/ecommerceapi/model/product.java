package com.ws101.senobiorayandayan.ecommerceapi.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a product entity in the e-commerce system.
 * This class serves as the data model for product catalog operations.
 * 
 * @author Ray Andayan Senobio
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class product {
    
    /**
     * Unique identifier for the product.
     * Generated automatically when a new product is created.
     */
    private Long id;
    
    /**
     * Product name - must be unique and descriptive.
     * Cannot be null or empty.
     */
    private String name;
    
    /**
     * Detailed description of the product.
     * Includes features, specifications, and usage information.
     */
    private String description;
    
    /**
     * Product price in USD.
     * Must be a positive value greater than zero.
     */
    private double price;
    
    /**
     * Product category (e.g., Electronics, Clothing, Books, etc.)
     * Used for filtering and organization.
     */
    private String category;
    
    /**
     * Available stock quantity.
     * Must be non-negative (zero or positive integer).
     */
    private int stockQuantity;
    
    /**
     * Optional URL link to product image.
     * Can be null if no image is available.
     */
    private String imageUrl;
    
    /**
     * Constructor for creating a product without an ID.
     * Useful when creating new products before ID generation.
     * 
     * @param name Product name
     * @param description Product description
     * @param price Product price
     * @param category Product category
     * @param stockQuantity Available stock quantity
     * @param imageUrl Optional image URL
     */
    public product(String name, String description, double price, 
                   String category, int stockQuantity, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }
}
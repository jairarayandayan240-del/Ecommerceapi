package com.ws101.senobiorayandayan.ecommerceapi.exception;

/**
 * Exception thrown when a requested product is not found in the catalog.
 * 
 * @author Ray Andayan Senobio
 * @version 1.0
 */
public class ProductNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     * 
     * @param id The ID of the product that was not found
     */
    public ProductNotFoundException(Long id) {
        super("Product with ID " + id + " was not found");
    }
    
    /**
     * Constructs a new ProductNotFoundException with custom message.
     * 
     * @param message The detail message
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
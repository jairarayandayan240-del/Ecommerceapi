package com.ws101.senobiorayandayan.ecommerceapi.controller;

import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import com.ws101.senobiorayandayan.ecommerceapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling product-related API endpoints.
 * Acts as the bridge between frontend and service layer.
 * 
 * @author Ray Andayan Senobio
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;
    
    /**
     * Constructor injection of ProductService dependency.
     * 
     * @param productService The product service instance
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * Retrieves all products from the catalog.
     * GET /api/v1/products
     * 
     * @return ResponseEntity containing list of all products and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    /**
     * Retrieves a single product by its ID.
     * GET /api/v1/products/{id}
     * 
     * @param id The product ID to retrieve
     * @return ResponseEntity with product and HTTP 200 OK if found,
     *         HTTP 404 Not Found if product doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Filters products by various criteria.
     * GET /api/v1/products/filter?filterType={type}&filterValue={value}
     * 
     * @param filterType The type of filter (category, price, name)
     * @param filterValue The value to filter by
     * @return ResponseEntity containing filtered list and HTTP 200 OK,
     *         or HTTP 400 Bad Request if filter type is invalid
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        try {
            List<Product> filteredProducts = productService.filterProducts(filterType, filterValue);
            return ResponseEntity.ok(filteredProducts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Creates a new product.
     * POST /api/v1/products
     * 
     * @param product The product to create (without ID)
     * @return ResponseEntity with created product and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    
    /**
     * Replaces an entire product (full update).
     * PUT /api/v1/products/{id}
     * 
     * @param id The ID of the product to replace
     * @param product The complete product data to replace with
     * @return ResponseEntity with updated product and HTTP 200 OK if found,
     *         HTTP 404 Not Found if product doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Partially updates a product (only provided fields).
     * PATCH /api/v1/products/{id}
     * 
     * @param id The ID of the product to patch
     * @param product The product data containing fields to update
     * @return ResponseEntity with updated product and HTTP 200 OK if found,
     *         HTTP 404 Not Found if product doesn't exist
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.patchProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Deletes a product by ID.
     * DELETE /api/v1/products/{id}
     * 
     * @param id The ID of the product to delete
     * @return ResponseEntity with HTTP 204 No Content if deleted,
     *         HTTP 404 Not Found if product doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
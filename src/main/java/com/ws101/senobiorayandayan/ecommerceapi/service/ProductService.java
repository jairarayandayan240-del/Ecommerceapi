package com.ws101.senobiorayandayan.ecommerceapi.service;

import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for product-related operations.
 * Manages in-memory product data storage using List collection.
 * 
 * @author Ray Andayan Senobio
 * @version 1.0
 */
@Service
public class ProductService {
    
    /**
     * In-memory storage for products using ArrayList.
     * Chosen for its dynamic sizing and efficient random access.
     * Note: Data is volatile and will be lost when application stops.
     */
    private final List<Product> productList = new ArrayList<>();
    
    /**
     * Counter for generating unique product IDs.
     * Starts at 1 and increments for each new product.
     * ID Generation Strategy: Simple counter based on list size + 1
     */
    private Long nextId = 1L;
    
    /**
     * Constructor initializes the product catalog with sample data.
     * Creates 10+ products for testing and demonstration purposes.
     */
    public ProductService() {
        initializeSampleData();
    }
    
    /**
     * Initializes the product catalog with sample products.
     * Creates a diverse range of products across different categories.
     */
    private void initializeSampleData() {
        // Electronics Category
        productList.add(new Product(nextId++, "Apple iPhone 15 Pro", 
            "Latest Apple smartphone with A17 Pro chip and titanium design", 
            1099.99, "Electronics", 50, "https://example.com/iphone15pro.jpg"));
        
        productList.add(new Product(nextId++, "Samsung Galaxy S24 Ultra", 
            "Premium Android phone with AI features and S Pen", 
            1199.99, "Electronics", 35, "https://example.com/samsungs24.jpg"));
        
        productList.add(new Product(nextId++, "Sony WH-1000XM5", 
            "Industry-leading noise cancelling headphones", 
            399.99, "Electronics", 100, "https://example.com/sonyxm5.jpg"));
        
        // Clothing Category
        productList.add(new Product(nextId++, "Nike Air Max 90", 
            "Classic running shoes with visible Air cushioning", 
            120.00, "Clothing", 200, "https://example.com/airmax90.jpg"));
        
        productList.add(new Product(nextId++, "Levi's 501 Original Jeans", 
            "Timeless straight-fit jeans made from durable denim", 
            89.99, "Clothing", 150, "https://example.com/levis501.jpg"));
        
        productList.add(new Product(nextId++, "The North Face Jacket", 
            "Water-resistant insulated jacket for cold weather", 
            249.99, "Clothing", 75, "https://example.com/northface.jpg"));
        
        // Books Category
        productList.add(new Product(nextId++, "Clean Code by Robert Martin", 
            "Handbook of agile software craftsmanship", 
            45.99, "Books", 300, "https://example.com/cleancode.jpg"));
        
        productList.add(new Product(nextId++, "Design Patterns: Elements of Reusable OO Software", 
            "Gang of Four book on classic software design patterns", 
            54.99, "Books", 120, "https://example.com/designpatterns.jpg"));
        
        // Home & Living Category
        productList.add(new Product(nextId++, "Instant Pot Duo 7-in-1", 
            "Multi-use pressure cooker with 7 cooking functions", 
            89.99, "Home & Living", 80, "https://example.com/instantpot.jpg"));
        
        productList.add(new Product(nextId++, "Dyson V15 Vacuum", 
            "Powerful cordless vacuum with laser detection", 
            699.99, "Home & Living", 25, "https://example.com/dysonv15.jpg"));
        
        productList.add(new Product(nextId++, "Philips Hue Smart Bulbs", 
            "Wi-Fi enabled color-changing LED bulbs (2-pack)", 
            49.99, "Home & Living", 150, "https://example.com/philipshue.jpg"));
    }
    
    /**
     * Retrieves all products from the catalog.
     * 
     * @return List of all products (returns empty list if none exist)
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }
    
    /**
     * Finds a product by its unique identifier.
     * 
     * @param id The product ID to search for
     * @return Optional containing the product if found, empty otherwise
     */
    public Optional<Product> getProductById(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
    
    /**
     * Creates a new product and adds it to the catalog.
     * Generates a unique ID automatically for the new product.
     * 
     * @param product The product to create (without ID)
     * @return The created product with generated ID
     */
    public Product createProduct(Product product) {
        Product newProduct = new Product(
            nextId++,
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory(),
            product.getStockQuantity(),
            product.getImageUrl()
        );
        productList.add(newProduct);
        return newProduct;
    }
    
    /**
     * Updates an entire existing product (full replacement).
     * 
     * @param id The ID of the product to update
     * @param updatedProduct The new product data
     * @return Optional containing the updated product if found
     */
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImageUrl(updatedProduct.getImageUrl());
            return Optional.of(product);
        }
        
        return Optional.empty();
    }
    
    /**
     * Partially updates an existing product (only provided fields).
     * Uses null-check to update only non-null fields from the request.
     * 
     * @param id The ID of the product to patch
     * @param patchData Product containing only fields to update
     * @return Optional containing the updated product if found
     */
    public Optional<Product> patchProduct(Long id, Product patchData) {
        Optional<Product> existingProduct = getProductById(id);
        
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            
            // Only update fields that are provided (non-null)
            if (patchData.getName() != null) {
                product.setName(patchData.getName());
            }
            if (patchData.getDescription() != null) {
                product.setDescription(patchData.getDescription());
            }
            if (patchData.getPrice() > 0) {
                product.setPrice(patchData.getPrice());
            }
            if (patchData.getCategory() != null) {
                product.setCategory(patchData.getCategory());
            }
            if (patchData.getStockQuantity() >= 0) {
                product.setStockQuantity(patchData.getStockQuantity());
            }
            if (patchData.getImageUrl() != null) {
                product.setImageUrl(patchData.getImageUrl());
            }
            
            return Optional.of(product);
        }
        
        return Optional.empty();
    }
    
    /**
     * Deletes a product from the catalog by ID.
     * 
     * @param id The ID of the product to delete
     * @return true if product was deleted, false if not found
     */
    public boolean deleteProduct(Long id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }
    
    /**
     * Filters products by category.
     * 
     * @param category The category to filter by (case-sensitive)
     * @return List of products matching the category
     */
    public List<Product> filterByCategory(String category) {
        return productList.stream()
                .filter(product -> product.getCategory() != null && product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Filters products by maximum price (products with price <= maxPrice).
     * 
     * @param maxPrice The maximum price threshold
     * @return List of products with price less than or equal to maxPrice
     */
    public List<Product> filterByPrice(double maxPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    /**
     * Filters products by name containing the search term (case-insensitive).
     * 
     * @param name The name or partial name to search for
     * @return List of products with names containing the search term
     */
    public List<Product> filterByName(String name) {
        return productList.stream()
                .filter(product -> product.getName() != null && product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Generic filter method that delegates to specific filter methods.
     * 
     * @param filterType The type of filter (category, price, name)
     * @param filterValue The value to filter by
     * @return List of products matching the filter criteria
     * @throws IllegalArgumentException if filterType is not recognized
     */
    public List<Product> filterProducts(String filterType, String filterValue) {
        switch (filterType.toLowerCase()) {
            case "category":
                return filterByCategory(filterValue);
            case "price":
                try {
                    double maxPrice = Double.parseDouble(filterValue);
                    return filterByPrice(maxPrice);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid price value: " + filterValue);
                }
            case "name":
                return filterByName(filterValue);
            default:
                throw new IllegalArgumentException("Unknown filter type: " + filterType + 
                    ". Supported types: category, price, name");
        }
    }
    
    /**
     * Returns the total number of products in the catalog.
     * 
     * @return Size of product list
     */
    public int getProductCount() {
        return productList.size();
    }
}
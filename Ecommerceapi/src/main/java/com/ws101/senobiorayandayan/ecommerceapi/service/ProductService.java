 package com.ws101.senobiorayandayan.ecommerceapi.service;

import com.ws101.senobiorayandayan.ecommerceapi.model.Category;
import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import com.ws101.senobiorayandayan.ecommerceapi.exception.ProductNotFoundException;
import com.ws101.senobiorayandayan.ecommerceapi.repository.CategoryRepository;
import com.ws101.senobiorayandayan.ecommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initSampleData() {
        if (productRepository.count() == 0) {
            Category electronics = categoryRepository.save(new Category(null, "Electronics", null));
            Category clothing    = categoryRepository.save(new Category(null, "Clothing", null));
            Category books       = categoryRepository.save(new Category(null, "Books", null));
            Category home        = categoryRepository.save(new Category(null, "Home & Living", null));

            productRepository.save(new Product("Apple iPhone 15 Pro",
                "Latest Apple smartphone with A17 Pro chip and titanium design",
                1099.99, electronics, 50, "https://example.com/iphone15pro.jpg"));
            productRepository.save(new Product("Samsung Galaxy S24 Ultra",
                "Premium Android phone with AI features and S Pen",
                1199.99, electronics, 35, "https://example.com/samsungs24.jpg"));
            productRepository.save(new Product("Sony WH-1000XM5",
                "Industry-leading noise cancelling headphones",
                399.99, electronics, 100, "https://example.com/sonyxm5.jpg"));

            productRepository.save(new Product("Nike Air Max 90",
                "Classic running shoes with visible Air cushioning",
                120.00, clothing, 200, "https://example.com/airmax90.jpg"));
            productRepository.save(new Product("Levi's 501 Original Jeans",
                "Timeless straight-fit jeans made from durable denim",
                89.99, clothing, 150, "https://example.com/levis501.jpg"));
            productRepository.save(new Product("The North Face Jacket",
                "Water-resistant insulated jacket for cold weather",
                249.99, clothing, 75, "https://example.com/northface.jpg"));

            productRepository.save(new Product("Clean Code by Robert Martin",
                "Handbook of agile software craftsmanship",
                45.99, books, 300, "https://example.com/cleancode.jpg"));
            productRepository.save(new Product("Design Patterns: Elements of Reusable OO Software",
                "Gang of Four book on classic software design patterns",
                54.99, books, 120, "https://example.com/designpatterns.jpg"));

            productRepository.save(new Product("Instant Pot Duo 7-in-1",
                "Multi-use pressure cooker with 7 cooking functions",
                89.99, home, 80, "https://example.com/instantpot.jpg"));
            productRepository.save(new Product("Dyson V15 Vacuum",
                "Powerful cordless vacuum with laser detection",
                699.99, home, 25, "https://example.com/dysonv15.jpg"));
            productRepository.save(new Product("Philips Hue Smart Bulbs",
                "Wi-Fi enabled color-changing LED bulbs (2-pack)",
                49.99, home, 150, "https://example.com/philipshue.jpg"));
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        if (product.getCategory() != null) {
            Category cat = product.getCategory();
            if (cat.getId() == null && cat.getName() != null) {
                Category existing = categoryRepository.findByName(cat.getName());
                if (existing != null) {
                    product.setCategory(existing);
                } else {
                    categoryRepository.save(cat);
                }
            }
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());
        return productRepository.save(existing);
    }

    public Product patchProduct(Long id, Product patchData) {
        Product existing = getProductById(id);
        if (patchData.getName() != null) existing.setName(patchData.getName());
        if (patchData.getDescription() != null) existing.setDescription(patchData.getDescription());
        if (patchData.getPrice() > 0) existing.setPrice(patchData.getPrice());
        if (patchData.getCategory() != null) existing.setCategory(patchData.getCategory());
        if (patchData.getStockQuantity() >= 0) existing.setStockQuantity(patchData.getStockQuantity());
        if (patchData.getImageUrl() != null) existing.setImageUrl(patchData.getImageUrl());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategoryNameIgnoreCase(category);
    }

    public List<Product> filterByPrice(double maxPrice) {
        return productRepository.findProductsInPriceRange(0, maxPrice);
    }

    public List<Product> filterByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterProducts(String filterType, String filterValue) {
        switch (filterType.toLowerCase()) {
            case "category": return filterByCategory(filterValue);
            case "price":
                try {
                    return filterByPrice(Double.parseDouble(filterValue));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid price value: " + filterValue);
                }
            case "name": return filterByName(filterValue);
            default: throw new IllegalArgumentException("Unknown filter type: " + filterType);
        }
    }

    public int getProductCount() {
        return (int) productRepository.count();
    }
}
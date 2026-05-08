 package com.ws101.senobiorayandayan.ecommerceapi.controller;

import com.ws101.senobiorayandayan.ecommerceapi.dto.CreateProductDto;
import com.ws101.senobiorayandayan.ecommerceapi.model.Category;
import com.ws101.senobiorayandayan.ecommerceapi.model.Product;
import com.ws101.senobiorayandayan.ecommerceapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("productDto", new CreateProductDto());
        return "admin";   // refers to templates/admin.html
    }

    @PostMapping("/products")
    public String createProduct(@Valid @ModelAttribute("productDto") CreateProductDto dto,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please fix the errors.");
            return "redirect:/admin";
        }

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());

        // Handle category
        if (dto.getCategoryName() != null && !dto.getCategoryName().trim().isEmpty()) {
            Category cat = new Category();
            cat.setName(dto.getCategoryName());
            product.setCategory(cat);
        }

        productService.createProduct(product);
        redirectAttributes.addFlashAttribute("success", "Product created successfully! (HTTP 201)");
        return "redirect:/admin";
    }
}
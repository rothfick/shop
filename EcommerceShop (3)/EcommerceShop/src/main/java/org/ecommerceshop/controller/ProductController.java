package org.ecommerceshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ecommerceshop.model.Product;
import org.ecommerceshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // Admin-only endpoint for creating a new product
    @PostMapping("/admin")
    @Operation(summary = "Create a new product", description = "This endpoint is for admin use only")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    // Admin-only endpoint for updating an existing product
    @PutMapping("/admin/{id}")
    @Operation(summary = "Update an existing product", description = "This endpoint is for admin use only")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.update(id, product);
    }

    // Admin-only endpoint for deleting a product by ID
    @DeleteMapping("/admin/{id}")
    @Operation(summary = "Delete a product by ID", description = "This endpoint is for admin use only")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


}

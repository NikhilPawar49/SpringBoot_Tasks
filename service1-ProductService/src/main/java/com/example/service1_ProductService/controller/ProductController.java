package com.example.service1_ProductService.controller;

import com.example.service1_ProductService.entity.Product;
import com.example.service1_ProductService.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "Operations related to products")
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Add new product")
    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.create(p);
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.get(id);
    }

    @Operation(summary = "Update product")
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return service.update(id, p);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @Operation(summary = "Reduce product stock", description = "Used by Order-Service via FEIGN client")
    @PutMapping("/reduceStock/{id}/{qty}")
    public boolean reduceStock(@PathVariable Long id, @PathVariable int qty) {
        return service.reduceStock(id, qty);
    }
}

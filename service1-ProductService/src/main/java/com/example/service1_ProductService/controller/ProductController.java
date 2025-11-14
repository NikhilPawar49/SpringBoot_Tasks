package com.example.service1_ProductService.controller;


import com.example.service1_ProductService.entity.Product;
import com.example.service1_ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.create(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

    // TO BE USED BY ORDER-SERVICE VIA FEIGN
    @PutMapping("/reduceStock/{id}/{qty}")
    public boolean reduceStock(@PathVariable Long id, @PathVariable int qty) {
        return service.reduceStock(id, qty);
    }
}


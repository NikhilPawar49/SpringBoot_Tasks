package com.example.service1_ProductService.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service1_ProductService.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}

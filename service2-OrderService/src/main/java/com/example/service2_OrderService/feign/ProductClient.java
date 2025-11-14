package com.example.service2_OrderService.feign;

import com.example.service2_OrderService.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://localhost:8081/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

    @PutMapping("/reduceStock/{id}/{qty}")
    Boolean reduceStock(@PathVariable("id") Long id, @PathVariable("qty") int qty);
}

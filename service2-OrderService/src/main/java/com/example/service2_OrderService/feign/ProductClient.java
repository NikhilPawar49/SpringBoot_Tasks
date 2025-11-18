package com.example.service2_OrderService.feign;

import com.example.product.api.ProductApiApi;
import com.example.service2_OrderService.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8081"
)
public interface ProductClient extends ProductApiApi {
}


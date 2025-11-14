package com.example.service2_OrderService.controller;

import com.example.service2_OrderService.entity.OrderEntity;
import com.example.service2_OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/place")
    public OrderEntity placeOrder(
            @RequestParam Long productId,
            @RequestParam int qty) {
        return service.placeOrder(productId, qty);
    }

    @GetMapping("/{id}")
    public OrderEntity getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }
}


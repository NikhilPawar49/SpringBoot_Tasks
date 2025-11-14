package com.example.service2_OrderService.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private double amount;
    private String status;
}


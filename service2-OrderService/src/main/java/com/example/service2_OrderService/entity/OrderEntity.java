package com.example.service2_OrderService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantity;
    private double totalPrice;
    private String orderStatus;     // SUCCESS / FAILED
    private String paymentStatus; // SUCCESS / FAILED
}

package com.example.service2_OrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service2_OrderService.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}

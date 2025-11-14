package com.example.service3_PaymentService.repository;

import com.example.service3_PaymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}

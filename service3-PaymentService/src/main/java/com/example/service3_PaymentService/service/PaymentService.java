package com.example.service3_PaymentService.service;

import com.example.service3_PaymentService.entity.Payment;
import com.example.service3_PaymentService.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repo;

    public Payment doPayment(Long orderId, double amount) {

        // Mocked logic
        String status = amount > 0 ? "SUCCESS" : "FAILED";

        Payment payment = Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .status(status)
                .build();

        return repo.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return repo.findById(id).orElse(null);
    }
}

package com.example.service3_PaymentService.controller;

import com.example.service3_PaymentService.entity.Payment;
import com.example.service3_PaymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/pay")
    public Payment pay(
            @RequestParam Long orderId,
            @RequestParam double amount
    ) {
        return service.doPayment(orderId, amount);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return service.getPaymentById(id);
    }
}

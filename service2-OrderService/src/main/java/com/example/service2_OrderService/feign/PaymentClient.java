package com.example.service2_OrderService.feign;

import com.example.service2_OrderService.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:8085/payment")
public interface PaymentClient {

    @PostMapping("/pay")
    PaymentResponse doPayment(
            @RequestParam Long orderId,
            @RequestParam double amount
    );
}


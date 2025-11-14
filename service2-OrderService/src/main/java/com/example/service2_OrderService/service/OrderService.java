package com.example.service2_OrderService.service;

import com.example.service2_OrderService.dto.PaymentResponse;
import com.example.service2_OrderService.entity.OrderEntity;
import com.example.service2_OrderService.feign.PaymentClient;
import com.example.service2_OrderService.feign.ProductClient;
import com.example.service2_OrderService.repository.OrderRepository;
import com.example.service2_OrderService.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    public OrderEntity placeOrder(Long productId, int qty) {

        // Fetch product
        ProductResponse product = productClient.getProduct(productId);
        if (product == null) {
            return OrderEntity.builder()
                    .productId(productId)
                    .quantity(qty)
                    .orderStatus("FAILED - PRODUCT NOT FOUND")
                    .build();
        }

        //  Check stock
        if (product.getStock() < qty) {
            return OrderEntity.builder()
                    .productId(productId)
                    .quantity(qty)
                    .orderStatus("FAILED - NOT ENOUGH STOCK")
                    .build();
        }

        //  Reduce stock
        boolean reduced = productClient.reduceStock(productId, qty);
        if (!reduced) {
            return OrderEntity.builder()
                    .productId(productId)
                    .quantity(qty)
                    .orderStatus("FAILED - STOCK UPDATE FAILED")
                    .build();
        }

        double totalPrice = product.getPrice() * qty;

        // Call Payment Service
        PaymentResponse payment = paymentClient.doPayment(productId, totalPrice);

        String paymentStatus = payment.getStatus().equals("SUCCESS") ?
                "PAID" : "PAYMENT_FAILED";

        //  Save Order
        OrderEntity order = OrderEntity.builder()
                .productId(productId)
                .quantity(qty)
                .totalPrice(totalPrice)
                .orderStatus("SUCCESS")
                .paymentStatus(paymentStatus)
                .build();

        return repo.save(order);
    }

    public OrderEntity getOrder(Long id) {
        return repo.findById(id).orElse(null);
    }
}

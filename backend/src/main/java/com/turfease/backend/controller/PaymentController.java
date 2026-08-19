package com.turfease.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turfease.backend.entity.Payment;
import com.turfease.backend.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(
            @RequestParam Long bookingId) {

        Payment payment =
                paymentService.createPayment(
                        bookingId
                );

        return ResponseEntity.ok(payment);
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<Payment> confirmPayment(
            @PathVariable Long paymentId) {

        Payment payment =
                paymentService.confirmPayment(
                        paymentId
                );

        return ResponseEntity.ok(payment);
    }
}
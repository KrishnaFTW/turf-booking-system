package com.turfease.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfease.backend.entity.Payment;
import com.turfease.backend.entity.PaymentStatus;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    Optional<Payment> findByTransactionReference(
            String transactionReference
    );

    List<Payment> findByStatus(
            PaymentStatus status
    );

    boolean existsByBookingId(Long bookingId);
}
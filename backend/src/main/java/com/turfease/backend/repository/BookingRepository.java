package com.turfease.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfease.backend.entity.Booking;
import com.turfease.backend.entity.BookingStatus;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingReference(
            String bookingReference
    );

    Optional<Booking> findBySlotId(Long slotId);

    List<Booking> findByCustomerPhone(
            String customerPhone
    );

    List<Booking> findByCustomerEmail(
            String customerEmail
    );

    List<Booking> findByBookingStatus(
            BookingStatus bookingStatus
    );

    boolean existsBySlotId(Long slotId);
}
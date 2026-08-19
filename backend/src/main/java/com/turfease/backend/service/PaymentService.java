package com.turfease.backend.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turfease.backend.entity.Booking;
import com.turfease.backend.entity.BookingStatus;
import com.turfease.backend.entity.Payment;
import com.turfease.backend.entity.PaymentStatus;
import com.turfease.backend.entity.Slot;
import com.turfease.backend.entity.SlotStatus;
import com.turfease.backend.repository.BookingRepository;
import com.turfease.backend.repository.PaymentRepository;
import com.turfease.backend.repository.SlotRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            SlotRepository slotRepository) {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public Payment createPayment(Long bookingId) {

        // 1. Find booking
        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"
                                )
                        );

        // 2. Check booking status
        if (booking.getBookingStatus()
                != BookingStatus.PENDING) {

            throw new RuntimeException(
                    "Booking is not pending"
            );
        }

        // 3. Check whether payment already exists
        if (paymentRepository.existsByBookingId(
                bookingId)) {

            throw new RuntimeException(
                    "Payment already exists"
            );
        }

        // 4. Create payment
        Payment payment = new Payment();

        payment.setBooking(booking);

        payment.setAmount(
                booking.getAmount()
        );

        payment.setStatus(
                PaymentStatus.PENDING
        );

        paymentRepository.save(payment);

        return payment;
    }

    @Transactional
    public Payment confirmPayment(
            Long paymentId) {

        // 1. Find payment
        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"
                                )
                        );

        // 2. Prevent duplicate confirmation
        if (payment.getStatus()
                == PaymentStatus.PAID) {

            return payment;
        }

        Booking booking =
                payment.getBooking();

        // 3. Make sure booking is still pending
        if (booking.getBookingStatus()
                != BookingStatus.PENDING) {

            throw new RuntimeException(
                    "Booking is no longer pending"
            );
        }

        // 4. Mark payment as paid
        payment.setStatus(
                PaymentStatus.PAID
        );

        payment.setTransactionReference(
                generateTransactionReference()
        );

        payment.setPaidAt(
                LocalDateTime.now()
        );

        // 5. Confirm booking
        booking.setBookingStatus(
                BookingStatus.CONFIRMED
        );

        // 6. Book the slot
        Slot slot = booking.getSlot();

        if (slot.getStatus()
                != SlotStatus.RESERVED) {

            throw new RuntimeException(
                    "Slot is no longer reserved"
            );
        }

        slot.setStatus(
                SlotStatus.BOOKED
        );

        // 7. Save everything
        slotRepository.save(slot);
        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }

    private String generateTransactionReference() {

        String randomPart =
                UUID.randomUUID()
                        .toString()
                        .substring(0, 10)
                        .toUpperCase();

        return "TXN-" + randomPart;
    }
}
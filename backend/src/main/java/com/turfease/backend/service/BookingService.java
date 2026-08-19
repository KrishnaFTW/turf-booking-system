package com.turfease.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turfease.backend.entity.Booking;
import com.turfease.backend.entity.BookingStatus;
import com.turfease.backend.entity.Slot;
import com.turfease.backend.entity.SlotStatus;
import com.turfease.backend.repository.BookingRepository;
import com.turfease.backend.repository.SlotRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public BookingService(
            BookingRepository bookingRepository,
            SlotRepository slotRepository) {

        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public Booking createBooking(
            Long slotId,
            String customerName,
            String customerPhone,
            String customerEmail) {

        // 1. Find the slot
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(()
                        -> new RuntimeException(
                        "Slot not found"
                )
                );

        // 2. Check slot availability
        if (slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new RuntimeException(
                    "Slot is not available"
            );
        }

        // 3. Make sure no booking already exists
        if (bookingRepository.existsBySlotId(slotId)) {
            throw new RuntimeException(
                    "Slot already has a booking"
            );
        }

        // 4. Create booking
        Booking booking = new Booking();

        booking.setSlot(slot);

        booking.setCustomerName(
                customerName
        );

        booking.setCustomerPhone(
                customerPhone
        );

        booking.setCustomerEmail(
                customerEmail
        );

        // Never trust price from frontend
        booking.setAmount(
                slot.getPrice()
        );

        booking.setBookingReference(
                generateBookingReference()
        );

        booking.setBookingStatus(
                BookingStatus.PENDING
        );

        // PaymentStatus remains PENDING
        // 5. Temporarily reserve slot
        slot.setStatus(
                SlotStatus.RESERVED
        );

        // 6. Save slot
        slotRepository.save(slot);

        // 7. Save booking
        return bookingRepository.save(booking);
    }

    @Transactional
public void releaseExpiredBookings() {

    LocalDateTime now = LocalDateTime.now();

    List<Booking> bookings =
            bookingRepository.findByBookingStatus(
                    BookingStatus.PENDING
            );

    for (Booking booking : bookings) {

        if (booking.getExpiresAt() != null
                && !booking.getExpiresAt().isAfter(now)) {

            Slot slot = booking.getSlot();

            if (slot != null) {

                slot.setStatus(
                        SlotStatus.AVAILABLE
                );

                slotRepository.save(slot);
            }

            booking.setBookingStatus(
                    BookingStatus.EXPIRED
            );

            bookingRepository.save(booking);

            System.out.println(
                    "Expired booking: "
                    + booking.getId()
            );
        }
    }
}

    private String generateBookingReference() {

        String randomPart
                = UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        return "TE-" + randomPart;
    }
}

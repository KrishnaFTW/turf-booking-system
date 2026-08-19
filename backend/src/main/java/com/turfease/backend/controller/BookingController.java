package com.turfease.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turfease.backend.entity.Booking;
import com.turfease.backend.repository.BookingRepository;
import com.turfease.backend.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(
            BookingService bookingService,
            BookingRepository bookingRepository) {

        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long slotId,
            @RequestParam String customerName,
            @RequestParam String customerPhone,
            @RequestParam String customerEmail) {

        Booking booking =
                bookingService.createBooking(
                        slotId,
                        customerName,
                        customerPhone,
                        customerEmail
                );

        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{reference}")
    public ResponseEntity<Booking> getBookingByReference(
            @PathVariable String reference) {

        return bookingRepository
                .findByBookingReference(reference)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }
}
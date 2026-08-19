package com.turfease.backend.service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SlotScheduler {

    private final SlotGenerationService slotGenerationService;
    private final BookingService bookingService;

    public SlotScheduler(
            SlotGenerationService slotGenerationService,
            BookingService bookingService) {

        this.slotGenerationService = slotGenerationService;
        this.bookingService = bookingService;
    }

    @Scheduled(
            initialDelay = 10000,
            fixedRate = 60000
    )
    public void maintainSlots() {

        System.out.println(
                "===== SLOT SCHEDULER RUNNING ====="
        );

        LocalDate today = LocalDate.now();

        slotGenerationService.generateSlots(
                today,
                4
        );

        bookingService.releaseExpiredBookings();
    }
}
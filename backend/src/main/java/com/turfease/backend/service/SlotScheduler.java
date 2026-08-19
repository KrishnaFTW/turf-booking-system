package com.turfease.backend.service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SlotScheduler {

    private final SlotGenerationService slotGenerationService;

    public SlotScheduler(
            SlotGenerationService slotGenerationService) {

        this.slotGenerationService =
                slotGenerationService;
    }

    @Scheduled(
            initialDelay = 10000,
            fixedRate = 3600000
    )
    public void maintainSlots() {

        LocalDate today = LocalDate.now();

        slotGenerationService.generateSlots(
                today,
                4
        );
    }
}
package com.turfease.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turfease.backend.entity.Slot;
import com.turfease.backend.entity.SlotStatus;
import com.turfease.backend.repository.SlotRepository;
import com.turfease.backend.service.SlotGenerationService;

@RestController
@RequestMapping("/api/slots")
@CrossOrigin(origins = "http://localhost:5173")
public class SlotController {

    private final SlotRepository slotRepository;
    private final SlotGenerationService slotGenerationService;

    public SlotController(
            SlotRepository slotRepository,
            SlotGenerationService slotGenerationService) {

        this.slotRepository = slotRepository;
        this.slotGenerationService = slotGenerationService;
    }

    // Get all slots
    @GetMapping
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    // Get slots for a particular date
    @GetMapping("/date/{date}")
    public List<Slot> getSlotsByDate(
            @PathVariable LocalDate date) {

        return slotRepository.findBySlotDate(date);
    }

    // Get available slots for a particular date
    @GetMapping("/date/{date}/available")
    public List<Slot> getAvailableSlots(
            @PathVariable LocalDate date) {

        return slotRepository.findBySlotDateAndStatus(
                date,
                SlotStatus.AVAILABLE
        );
    }

    // Get slots for a particular turf and date
    @GetMapping("/turf/{turfId}/date/{date}")
    public List<Slot> getSlotsByTurfAndDate(
            @PathVariable Long turfId,
            @PathVariable LocalDate date) {

        return slotRepository.findByTurfIdAndSlotDate(
                turfId,
                date
        );
    }

    // Get slots for a particular turf, sport and date
    @GetMapping("/turf/{turfId}/sport/{sportId}/date/{date}")
    public List<Slot> getSlotsByTurfSportAndDate(
            @PathVariable Long turfId,
            @PathVariable Long sportId,
            @PathVariable LocalDate date) {

        return slotRepository.findByTurfIdAndSportIdAndSlotDate(
                turfId,
                sportId,
                date
        );
    }

    // Get available slots for a particular turf, sport and date
    @GetMapping("/turf/{turfId}/sport/{sportId}/date/{date}/available")
    public List<Slot> getAvailableSlotsByTurfSportAndDate(
            @PathVariable Long turfId,
            @PathVariable Long sportId,
            @PathVariable LocalDate date) {

        return slotRepository
                .findByTurfIdAndSportIdAndSlotDateAndStatus(
                        turfId,
                        sportId,
                        date,
                        SlotStatus.AVAILABLE
                );
    }

    // Generate slots for the next 4 days
    @PostMapping("/generate")
    public ResponseEntity<String> generateSlots() {

        slotGenerationService.generateSlots(
                LocalDate.now(),
                4
        );

        return ResponseEntity.ok(
                "Slots generated successfully for 4 days."
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(
            @PathVariable Long id) {

        return slotRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }
}

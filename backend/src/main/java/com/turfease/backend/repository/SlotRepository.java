package com.turfease.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfease.backend.entity.Slot;
import com.turfease.backend.entity.SlotStatus;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findBySlotDate(LocalDate slotDate);

    List<Slot> findBySlotDateAndStatus(
            LocalDate slotDate,
            SlotStatus status
    );

    List<Slot> findByTurfIdAndSlotDate(
            Long turfId,
            LocalDate slotDate
    );

    List<Slot> findByTurfIdAndSportIdAndSlotDate(
            Long turfId,
            Long sportId,
            LocalDate slotDate
    );

    List<Slot> findByTurfIdAndSportIdAndSlotDateAndStatus(
            Long turfId,
            Long sportId,
            LocalDate slotDate,
            SlotStatus status
    );
}
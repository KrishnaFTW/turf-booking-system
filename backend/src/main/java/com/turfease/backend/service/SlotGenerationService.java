package com.turfease.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turfease.backend.entity.Slot;
import com.turfease.backend.entity.SlotStatus;
import com.turfease.backend.entity.Sport;
import com.turfease.backend.entity.Turf;
import com.turfease.backend.repository.SlotRepository;
import com.turfease.backend.repository.SportRepository;
import com.turfease.backend.repository.TurfRepository;

@Service
public class SlotGenerationService {

    private final SlotRepository slotRepository;
    private final TurfRepository turfRepository;
    private final SportRepository sportRepository;

    public SlotGenerationService(
            SlotRepository slotRepository,
            TurfRepository turfRepository,
            SportRepository sportRepository) {

        this.slotRepository = slotRepository;
        this.turfRepository = turfRepository;
        this.sportRepository = sportRepository;
    }

    @Transactional
    public void generateSlots(
            LocalDate startDate,
            int numberOfDays) {

        List<Turf> turfs = turfRepository.findAll();
        List<Sport> sports = sportRepository.findAll();

        for (Turf turf : turfs) {

            if (!turf.isActive()) {
                continue;
            }

            if (turf.getOpeningTime() == null
                    || turf.getClosingTime() == null) {
                continue;
            }

            for (Sport sport : sports) {

                if (!sport.isActive()) {
                    continue;
                }

                for (int day = 0; day < numberOfDays; day++) {

                    LocalDate date
                            = startDate.plusDays(day);

                    generateSlotsForDate(
                            turf,
                            sport,
                            date
                    );
                }
            }
        }
    }

    private void generateSlotsForDate(
            Turf turf,
            Sport sport,
            LocalDate date) {

        LocalTime currentTime
                = turf.getOpeningTime();

        LocalTime closingTime
                = turf.getClosingTime();

        while (currentTime.isBefore(closingTime)) {

            LocalTime endTime
                    = currentTime.plusHours(1);

            if (endTime.isAfter(closingTime)) {
                break;
            }

            boolean slotExists
                    = slotRepository.existsByTurfIdAndSportIdAndSlotDateAndStartTime(
                            turf.getId(),
                            sport.getId(),
                            date,
                            currentTime
                    );

            if (!slotExists) {

                Slot slot = new Slot();

                slot.setTurf(turf);
                slot.setSport(sport);
                slot.setSlotDate(date);

                slot.setStartTime(currentTime);
                slot.setEndTime(endTime);

                BigDecimal price;

                if (currentTime.isBefore(
                        LocalTime.of(18, 0))) {

                    price = sport.getDayPrice();

                } else {

                    price = sport.getNightPrice();
                }

                slot.setPrice(price);

                slot.setStatus(
                        SlotStatus.AVAILABLE
                );

                slotRepository.save(slot);
            }

            currentTime = endTime;
        }
    }
}

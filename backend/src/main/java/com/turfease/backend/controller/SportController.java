package com.turfease.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turfease.backend.entity.Sport;
import com.turfease.backend.repository.SportRepository;

@RestController
@RequestMapping("/api/sports")
@CrossOrigin(origins = "http://localhost:5173")
public class SportController {

    private final SportRepository sportRepository;

    public SportController(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @GetMapping
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSportById(
            @PathVariable Long id) {

        return sportRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sport createSport(@RequestBody Sport sport) {
        return sportRepository.save(sport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> updateSport(
            @PathVariable Long id,
            @RequestBody Sport updatedSport) {

        return sportRepository.findById(id)
                .map(sport -> {

                    
                    sport.setName(updatedSport.getName());
                    sport.setDayPrice(updatedSport.getDayPrice());
                    sport.setNightPrice(updatedSport.getNightPrice());
                    sport.setActive(updatedSport.isActive());

                    return ResponseEntity.ok(
                            sportRepository.save(sport)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(
            @PathVariable Long id) {

        if (!sportRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        sportRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

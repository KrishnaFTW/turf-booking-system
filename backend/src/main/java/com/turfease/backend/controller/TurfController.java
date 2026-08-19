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

import com.turfease.backend.entity.Turf;
import com.turfease.backend.repository.TurfRepository;

@RestController
@RequestMapping("/api/turfs")
@CrossOrigin(origins = "http://localhost:5173")
public class TurfController {

    private final TurfRepository turfRepository;

    public TurfController(TurfRepository turfRepository) {
        this.turfRepository = turfRepository;
    }

    @GetMapping
    public List<Turf> getAllTurfs() {
        return turfRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turf> getTurfById(
            @PathVariable Long id) {

        return turfRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turf createTurf(@RequestBody Turf turf) {
        return turfRepository.save(turf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turf> updateTurf(
            @PathVariable Long id,
            @RequestBody Turf updatedTurf) {

        return turfRepository.findById(id)
                .map(turf -> {

                    turf.setName(updatedTurf.getName());
                    turf.setLocation(updatedTurf.getLocation());
                    turf.setDescription(updatedTurf.getDescription());
                    turf.setOpeningTime(updatedTurf.getOpeningTime());
                    turf.setClosingTime(updatedTurf.getClosingTime());
                    turf.setActive(updatedTurf.isActive());

                    return ResponseEntity.ok(
                            turfRepository.save(turf)
                    );
                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurf(
            @PathVariable Long id) {

        if (!turfRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        turfRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

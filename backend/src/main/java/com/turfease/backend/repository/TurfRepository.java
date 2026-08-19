package com.turfease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfease.backend.entity.Turf;

public interface TurfRepository extends JpaRepository<Turf, Long> {
}
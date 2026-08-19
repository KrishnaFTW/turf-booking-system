package com.turfease.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfease.backend.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {
}
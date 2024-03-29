package com.example.taxi.repository;

import com.example.taxi.entity.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi, Integer> {
}

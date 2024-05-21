package com.example.colorfinder.repository;

import com.example.colorfinder.entity.PERSONALCOLOR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalColorRepository extends JpaRepository<PERSONALCOLOR, Long> {
    Optional<PERSONALCOLOR> findByColorId(String colorId);
}

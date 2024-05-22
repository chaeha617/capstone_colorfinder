package com.example.colorfinder.repository;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByUserId(Long userId);
}

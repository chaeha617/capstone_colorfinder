package com.example.colorfinder.repository;

import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);

    @Query("SELECT MAX(a.orderId) FROM OrderEntity a WHERE a.userId = :userId")
    Long findMaxAddIdByUserId(@Param("userId") Long userId);
}

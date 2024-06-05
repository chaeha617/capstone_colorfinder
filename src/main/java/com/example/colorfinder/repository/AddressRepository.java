package com.example.colorfinder.repository;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.entity.AddressEntity;
import com.example.colorfinder.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByUserId(Long userId);
    AddressEntity findByAddId(Long addId);
    List<AddressEntity> findByAddIdIn(Set<Long> addIds);

    @Query("SELECT MIN(a.addId) FROM AddressEntity a WHERE a.userId = :userId")
    Long findMinAddIdByUserId(@Param("userId") Long userId);

}
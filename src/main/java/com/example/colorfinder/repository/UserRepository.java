package com.example.colorfinder.repository;


import com.example.colorfinder.entity.USERS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<USERS, Long> {
    Optional<USERS> findByEmail(String email); // 이메일로 사용자 정보를 가져옴
}
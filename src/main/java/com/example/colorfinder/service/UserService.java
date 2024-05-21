package com.example.colorfinder.service;

import com.example.colorfinder.dto.AddUserRequest;
import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.entity.PERSONALCOLOR;
import com.example.colorfinder.repository.UserRepository;
import com.example.colorfinder.repository.PersonalColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonalColorRepository personalColorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        // PERSONALCOLOR 객체 생성 또는 기존 객체 조회
        PERSONALCOLOR personalColor = personalColorRepository.findByColorId(dto.getColorId())
                .orElse(new PERSONALCOLOR(dto.getColorId()));

        // PERSONALCOLOR 엔티티를 저장하고 영속 상태로 만듭니다.
        personalColor = personalColorRepository.save(personalColor);

        // USER 객체 생성 및 저장
        return userRepository.save(USERS.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .gender(dto.getGender())
                .personalColor(personalColor)
                .build()).getUserId();
    }

    @Transactional
    public void updateUser(Long userId, UpdateUserRequest request) {
        // 사용자 정보 조회
        USERS user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID에 해당하는 사용자를 찾을 수 없습니다: " + userId));

        // 닉네임 변경
        user.setNickname(request.getNickname());

        // 퍼스널 컬러 변경
        PERSONALCOLOR personalColor = personalColorRepository.findByColorId(request.getColorId())
                .orElseThrow(() -> new IllegalArgumentException("해당 퍼스널 컬러를 찾을 수 없습니다: " + request.getColorId()));
        user.setPersonalColor(personalColor);

        // 변경된 정보 저장
        userRepository.save(user);
    }
    public USERS getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID에 해당하는 사용자를 찾을 수 없습니다: " + userId));
    }

}
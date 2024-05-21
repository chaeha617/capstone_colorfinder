package com.example.colorfinder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private Long userId;
    private String email;
    private String password;
    private String nickname;
    private String colorId; // 수정된 부분: @Getter 어노테이션 제거
    private String gender;
}

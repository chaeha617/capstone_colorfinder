package com.example.colorfinder.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String nickname;
    private String colorId;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String nickname, String colorId) {
        this.nickname = nickname;
        this.colorId = colorId;
    }

    // Getter와 Setter 메소드
}

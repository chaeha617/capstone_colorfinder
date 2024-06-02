package com.example.colorfinder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalColorUpdateRequest {
    private Long userId;
    private String colorId;
}

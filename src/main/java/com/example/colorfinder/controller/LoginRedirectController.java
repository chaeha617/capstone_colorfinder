package com.example.colorfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 로그인 페이지를 보여줍니다.
    }
}

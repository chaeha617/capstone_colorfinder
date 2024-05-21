package com.example.colorfinder.controller;

import com.example.colorfinder.dto.AddUserRequest;
import com.example.colorfinder.entity.USERS;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.colorfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/main")
    public String main(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName(); // 현재 사용자의 ID를 가져옵니다.

        model.addAttribute("userId", userId);
        return "main";
    }

    @GetMapping("/InformationUpdate")
    public String informationUpdate() {
        return "InformationUpdate"; // 뷰 이름을 반환하도록 수정
    }

}

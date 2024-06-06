package com.example.colorfinder.controller;

import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.service.UpdateUserRequest;
import com.example.colorfinder.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/update")
    public String redirectToInformationUpdate() {
        return "redirect:/InformationUpdate";
    }

    @GetMapping("/USERS/update")
    public String updateUser(Model model) {
        // 현재 사용자의 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Principal 객체 가져오기
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Principal 객체에서 사용자 ID(Long) 가져오기
        Long userId = Long.parseLong(userDetails.getUsername());

        // 사용자 정보 가져오기
        USERS user = userService.getUserById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("colorId", user.getPersonalColor().getColorId());
        return "InformationUpdate";
    }


    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute UpdateUserRequest request) {
        // 현재 사용자의 ID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userIdString = authentication.getName();

        // String으로 된 사용자 ID를 Long 타입으로 변환
        Long userId = Long.parseLong(userIdString);

        // 사용자 정보 업데이트
        userService.updateUser(userId, request);
        return "redirect:/USERS/update";
    }

}

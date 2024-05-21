package com.example.colorfinder.controller;

import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.service.UpdateUserRequest;
import com.example.colorfinder.service.UserService;
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

    @GetMapping("/USERS/{userId}/update")
    public String updateUser(@PathVariable Long userId, Model model) {
        USERS user = userService.getUserById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("colorId", user.getPersonalColor().getColorId()); // 퍼스널 컬러의 이름만 가져옵니다.
        // 이후 해당 뷰로 이동하여 사용자 정보를 표시하도록 함
        return "InformationUpdate";
    }
    @PostMapping("/users/{userId}/update")
    public String updateUser(@PathVariable Long userId, @ModelAttribute UpdateUserRequest request) {
        userService.updateUser(userId, request);
        return "redirect:/USERS/" + userId + "/update"; // 수정된 정보를 반영한 페이지로 리디렉션
    }


}
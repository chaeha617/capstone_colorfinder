package com.example.colorfinder.controller;

import com.example.colorfinder.dto.PersonalColorUpdateRequest;
import com.example.colorfinder.entity.PERSONALCOLOR;
import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.repository.UserRepository;
import com.example.colorfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ColorViewController {
    private final UserService userService;

    public ColorViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/percol01")
    public String percol01() {
        return "percol01";
    }
    @GetMapping("/updatePersonalColor")
    public String redirectToUpdatePersonalColor() {
        return "redirect:/colorfinder"; // personalColorUpdate는 실제로 해당 페이지의 URL입니다.
    }
    @Autowired
    private UserRepository userRepository; // UserRepository는 사용자 엔티티를 다루는 Spring Data JPA 리포지토리입니다.

    @PostMapping("/updatePersonalColor")
    public String updatePersonalColor(@RequestBody PersonalColorUpdateRequest request) {
        // 현재 로그인한 사용자의 아이디 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // 사용자 엔티티 조회
        Optional<USERS> optionalUser = userRepository.findById(Long.parseLong(userId));
        if (optionalUser.isPresent()) {
            USERS user = optionalUser.get();

            // 사용자의 퍼스널 컬러 업데이트
            PERSONALCOLOR personalColor = new PERSONALCOLOR();
            personalColor.setColorId(request.getColorId());
            user.setPersonalColor(personalColor);
            userRepository.save(user); // 변경된 사용자 엔티티 저장

            return "redirect:/colorfinder";
        } else {
            return "redirect:/colorfinder";
        }
    }

    @GetMapping("/WCview")
    public String showWCView() {
        return "WCview";
    }

    @GetMapping("/SCview")
    public String showSCView() {
        return "SCview";
    }

    @GetMapping("/AWview")
    public String showAWView() {
        return "AWview";
    }

    @GetMapping("/SWview")
    public String showSWView() {
        return "SWview";
    }
}

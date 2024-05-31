package com.example.colorfinder.interceptor;

import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Long userId = Long.parseLong(authentication.getName());
            USERS user = userService.getUserById(userId);
            request.setAttribute("userId", user.getUserId());
            request.setAttribute("userName", user.getNickname());

            request.setAttribute("loginFlag", "로그아웃");
            request.setAttribute("loginUrl", "/logout");
            request.setAttribute("userInfo", user.getNickname() + "님");
            request.setAttribute("infoUrl", "/cart/pay/saveInfo");
        }else {
            request.setAttribute("loginFlag", "로그인");
            request.setAttribute("loginUrl", "/login");
            request.setAttribute("userInfo", "회원가입");
            request.setAttribute("infoUrl", "/signup");
        }

        System.out.println("pre");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            String userName = (String) request.getAttribute("userName");

            modelAndView.addObject("loginFlag", "로그아웃");
            modelAndView.addObject("loginUrl", "/logout");
            modelAndView.addObject("userInfo", userName + "님");
            modelAndView.addObject("infoUrl", "/cart/pay/saveInfo");

        } catch (Exception e) {
            modelAndView.addObject("loginFlag", "로그인");
            modelAndView.addObject("loginUrl", "/login");
            modelAndView.addObject("userInfo", "회원가입");
            modelAndView.addObject("infoUrl", "/signup");
        }
        System.out.println("되고있음");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 이곳에 완료 후 처리 코드를 작성할 수 있음
    }
}

package com.example.colorfinder.interceptor;

import com.example.colorfinder.entity.USERS;
import com.example.colorfinder.repository.UserRepository;
import com.example.colorfinder.service.UserService;
import jakarta.annotation.Resource;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    Long userId = Long.parseLong(authentication.getName());
                    USERS user = userService.getUserById(userId);
                    modelAndView.addObject("loginFlag", "로그아웃");
                    modelAndView.addObject("loginUrl", "/logout");
                    System.out.println("=====================username : " + user.getUsername());
                    modelAndView.addObject("userInfo", (user.getNickname() == null || user.getNickname().isEmpty()) ? "손님" :user.getNickname() + "님");
                    modelAndView.addObject("infoUrl", "/cart/pay/saveInfo");
                }else{
                    modelAndView.addObject("loginFlag", "로그인");
                    modelAndView.addObject("loginUrl", "/login");
                    modelAndView.addObject("userInfo", "회원가입");
                    modelAndView.addObject("infoUrl", "/signup");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 이곳에 완료 후 처리 코드를 작성할 수 있음
    }
}

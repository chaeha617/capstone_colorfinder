package com.example.colorfinder;

import com.example.colorfinder.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addInterceptor(InterceptorRegistry registry){
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**");
    }
}

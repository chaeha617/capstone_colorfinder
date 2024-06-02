package com.example.colorfinder;

import com.example.colorfinder.interceptor.CustomInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@Slf4j
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/logo.png","/login/**","/signup/**"); // static 폴더 제외, 로그인 - 회원가입 페이지 제외
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**","/logo.png")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public CustomInterceptor customInterceptor(){
        return new CustomInterceptor();
    }

}

package com.example.colorfinder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ImageController {

    private final WebClient webClient;

    public ImageController() {
        this.webClient = WebClient.create("http://localhost:5555");
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("파일을 선택해주세요.");
        }

        try {
            // 현재 사용자의 ID를 가져옵니다.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();

            // 사용자 정보와 파일을 함께 전송
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            formData.add("file", file.getResource());
            formData.add("user_id", userId);

            String personalColorResponse = this.webClient.post()
                    .uri("/analyze")
                    .body(BodyInserters.fromMultipartData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return ResponseEntity.ok(personalColorResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 및 분석 실패", e);
        }
    }
}

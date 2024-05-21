package com.example.colorfinder.controller;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartController {
    private  final CartService cartService;

    @PostMapping("product/cart/{id}")
    public String saveCart(@ModelAttribute CartDTO cartDTO){

        cartService.save(cartDTO);

        return "Cart";
    }

}

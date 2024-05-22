package com.example.colorfinder.controller;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.repository.CartRepository;
import com.example.colorfinder.service.CartService;
import com.example.colorfinder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private final CartRepository cartRepository;

    @GetMapping("/cart/{id}")
    public String cartList(@PathVariable Long id, Model model){
        List<CartDTO> cartList = cartService.findByUserId(id);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @PostMapping("/cart/pay")
    public String payFor(@RequestParam("selectedProductIds") String selectedProductIds, Model model){
        List<Long> ids = Arrays.stream(selectedProductIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<CartDTO> orderList = cartService.findByCartIds(ids);
        model.addAttribute("orderList", orderList);
        return "payFor";
    }

    /*@PostMapping("/cart{id}/pay")
    public String payFor(@RequestBody List<Long> selectedProductIds) {
        // 선택된 상품 id를 이용하여 payFor 화면으로 이동 또는 다른 작업 수행
        return "redirect:/cart/{id}/pay"; // 또는 다른 페이지로 리다이렉트
    }*/
}

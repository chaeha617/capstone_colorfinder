package com.example.colorfinder.controller;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.repository.CartRepository;
import com.example.colorfinder.service.AddressService;
import com.example.colorfinder.service.CartService;
import com.example.colorfinder.service.OrderService;
import com.example.colorfinder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final OrderService orderService;
    private final AddressService addressService;

    @GetMapping("/cart")
    public String cartList(Model model){
        Long userId = null;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = Long.parseLong(authentication.getName());
        }catch (Exception e){
            return "redirect:/login";
        }

        List<CartDTO> cartList = cartService.findByUserId(userId);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @PostMapping("/cart/pay")
    public String payFor(@RequestParam("selectedProductIds") String selectedProductIds, Model model){
        Long userId = null;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = Long.parseLong(authentication.getName());
        }catch (Exception e){
            return "redirect:/login";
        }

        List<Long> ids = Arrays.stream(selectedProductIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<CartDTO> orderList = cartService.findByCartIds(ids);
        AddressDTO addressDTO = addressService.findMinAddIdByUserId(userId);

        model.addAttribute("orderList", orderList);
        model.addAttribute("address", addressDTO);
        return "payFor";
    }
}
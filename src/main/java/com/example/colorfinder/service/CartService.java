package com.example.colorfinder.service;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void save(CartDTO cartDTO){
        CartEntity cartEntity = CartEntity.toCartEntity(cartDTO);
        cartRepository.save(cartEntity);
    }

}

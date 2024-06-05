package com.example.colorfinder.service;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.ProductEntity;
import com.example.colorfinder.repository.CartRepository;
import com.example.colorfinder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    public List<CartDTO> findByUserId(Long userId){
        List<CartEntity> cartEntityList = cartRepository.findByUserId(userId);
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (CartEntity cartEntity : cartEntityList){
            cartDTOList.add(CartDTO.toCartDTO(cartEntity));
        }
        return cartDTOList;
    }

    public List<CartDTO> findByCartIds(List<Long> ids){
        List<CartEntity> cartEntities = cartRepository.findAllById(ids);
        return cartEntities.stream()
                .map(CartDTO::toCartDTO)
                .collect(Collectors.toList());
    }

    public Long save(CartDTO cartDTO){
        return cartRepository.save(CartEntity.toCartEntity(cartDTO)).getCartId();
    }
}
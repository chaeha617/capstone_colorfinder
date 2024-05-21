package com.example.colorfinder.dto;

import com.example.colorfinder.entity.CartEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartDTO {
    private Long cartId;
    private Integer cartCnt;
    private Long productId;
    private Long userId;
    private String productSize;

    public static CartDTO toCartDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cartEntity.getCartId());
        cartDTO.setCartCnt(cartEntity.getCartCnt());
        cartDTO.setProductId(cartEntity.getProductId());
        cartDTO.setUserId(cartEntity.getUserId());
        cartDTO.setProductSize(cartEntity.getProductSize());
        return cartDTO;
    }
}

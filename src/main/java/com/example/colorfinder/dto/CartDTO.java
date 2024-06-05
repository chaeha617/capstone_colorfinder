package com.example.colorfinder.dto;

import com.example.colorfinder.entity.CartEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartDTO {
    private Long cartId;
    private Integer cartCnt;
    private String productSize;
    private Long productId;
    private Long userId;
    private ProductDTO product;
    private String productName;
    private Integer productPrice;
    private Integer totalPrice;

    //private String formatPrice;

    public static CartDTO toCartDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cartEntity.getCartId());
        cartDTO.setCartCnt(cartEntity.getCartCnt());
        cartDTO.setProductSize(cartEntity.getProductSize());
        cartDTO.setProductId(cartEntity.getProduct().getProductId());
        cartDTO.setProductName(cartEntity.getProduct().getProductName());
        cartDTO.setProductPrice(cartEntity.getProduct().getProductPrice());
        cartDTO.setTotalPrice(cartEntity.getProduct().getProductPrice() * cartEntity.getCartCnt());
        cartDTO.setUserId(cartEntity.getUserId());

        //DecimalFormat priceFormat = new DecimalFormat("#,###,##0");
        //cartDTO.setFormatPrice(priceFormat.format(cartEntity.getProduct().getProductPrice()));
        return cartDTO;
    }
}
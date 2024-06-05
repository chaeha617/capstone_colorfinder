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
    private String imageUrl;
    private String priceFormat;
    private String totalFormat;


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

        DecimalFormat idFormat = new DecimalFormat("000");
        cartDTO.setImageUrl("/static/CrawligData/"+ cartEntity.getProduct().getCateId()+idFormat.format(cartEntity.getProduct().getProductId())+".jpg");

        DecimalFormat commaFormat = new DecimalFormat("#,###,##0");
        cartDTO.setPriceFormat(commaFormat.format(cartEntity.getProduct().getProductPrice()));
        cartDTO.setTotalFormat(commaFormat.format(cartEntity.getProduct().getProductPrice() * cartEntity.getCartCnt()));

        return cartDTO;
    }
}
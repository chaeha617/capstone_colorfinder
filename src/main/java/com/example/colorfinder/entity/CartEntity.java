package com.example.colorfinder.entity;

import com.example.colorfinder.dto.CartDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "CARTS")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Long cartId;

    @Column(name = "cartCnt")
    private Integer cartCnt;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "productSize")
    private String productSize;

    public static  CartEntity toCartEntity(CartDTO cartDTO){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartDTO.getCartId());
        cartEntity.setCartCnt(cartDTO.getCartCnt());
        cartEntity.setProductId(cartDTO.getProductId());
        cartEntity.setUserId(cartDTO.getUserId());
        cartEntity.setProductSize(cartDTO.getProductSize());
        return cartEntity;
    }
}

package com.example.colorfinder.entity;

import com.example.colorfinder.dto.CartDTO;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @Column(name = "productSize")
    private String productSize;

    @OneToOne(fetch = FetchType.LAZY) // 혹은 원하는 연관관계 설정에 따라 변경
    @JoinColumn(name = "productId") // 카트에 있는 제품의 ID를 참조
    private ProductEntity product;

    @Column(name = "userId")
    private Long userId;

    public static  CartEntity toCartEntity(CartDTO cartDTO){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartDTO.getCartId());
        cartEntity.setCartCnt(cartDTO.getCartCnt());
        cartEntity.setProductSize(cartDTO.getProductSize());
        cartEntity.setProduct(ProductEntity.toProductEntity(cartDTO.getProduct()));
        cartEntity.setUserId(cartDTO.getUserId());

        return cartEntity;
    }
}

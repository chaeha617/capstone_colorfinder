package com.example.colorfinder.entity;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.OrderDTO;
import com.example.colorfinder.dto.ProductDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor
@IdClass(OrderId.class)
public class OrderEntity {
    @Id
    @Column(name = "orderId")
    private Long orderId;

    @Id
    @Column(name = "productId")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY) // 혹은 원하는 연관관계 설정에 따라 변경
    @JoinColumn(name = "productId", insertable = false, updatable = false) // 복합 키로 사용하므로 외래 키는 insertable과 updatable을 false로 설정
    private ProductEntity product;

    @Column(name = "orderCnt")
    private Integer orderCnt;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "orderStatus")
    private String orderStatus;

    @Column(name = "totalPrice")
    private Integer totalPrice;

    @Column(name = "addId")
    private Long addId;

    @Column(name = "productSize")
    private String productSize;



    public static OrderEntity toOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDTO.getOrderId());
        orderEntity.setProductId(orderDTO.getProduct().getProductId());
        orderEntity.setOrderCnt(orderDTO.getOrderCnt());
        orderEntity.setOrderDate(LocalDateTime.now());
        orderEntity.setOrderStatus("배송전");
        orderEntity.setTotalPrice(orderDTO.getTotalPrice());
        orderEntity.setProductSize(orderDTO.getProductSize());
        orderEntity.setAddId(orderDTO.getAddId());
        orderEntity.setProduct(ProductEntity.toProductEntity(orderDTO.getProduct()));
        orderEntity.setUserId(orderDTO.getUserId());

        return orderEntity;
    }
}

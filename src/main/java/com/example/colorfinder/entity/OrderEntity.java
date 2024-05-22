package com.example.colorfinder.entity;

import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.OrderDTO;
import com.example.colorfinder.dto.ProductDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "orderCnt")
    private Integer orderCnt;

    /*@OneToOne(fetch = FetchType.LAZY) // 혹은 원하는 연관관계 설정에 따라 변경
    @JoinColumn(name = "productId") // 카트에 있는 제품의 ID를 참조*/
    @Column(name = "productId")
    private Long product;

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

    @Builder
    public OrderEntity(Long product, Long orderId, Integer orderCnt, Long userId, LocalDateTime orderDate, String orderStatus, Integer totalPrice, Long addId) {
        this.product = product;
        this.orderId = orderId;
        this.orderCnt = orderCnt;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.addId = addId;
    }

    /*public static  OrderEntity toOrderEntity(OrderDTO orderDTO){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDTO.getOrderId());
        orderEntity.setOrderCnt(orderDTO.getOrderCnt());
        orderEntity.setProduct(ProductEntity.toProductEntity(orderDTO.getProduct()));
        orderEntity.setUserId(orderDTO.getUserId());
        orderEntity.setOrderDate(orderDTO.getOrderDate());
        orderEntity.setOrderStatus(orderDTO.getOrderStatus());
        orderEntity.setTotalPrice(orderDTO.getTotalPrice());
        orderEntity.setAddId(orderDTO.getAddId());
        orderEntity.setProductSize(orderDTO.getProductSize());

        return orderEntity;
    }*/

}

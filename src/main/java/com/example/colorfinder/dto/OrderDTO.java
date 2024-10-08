package com.example.colorfinder.dto;

import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.OrderEntity;
import com.example.colorfinder.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long orderId;
    private Integer orderCnt;
    private ProductDTO product;
    private Long productId;
    private Long userId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private Integer totalPrice;
    private Long addId;
    private String productSize;
    private String productName;
    private Integer productPrice;
    private String imageUrl;
    private String priceFormat;
    private String totalFormat;


    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderEntity.getOrderId());
        orderDTO.setProductId(orderEntity.getProduct().getProductId());
        orderDTO.setOrderCnt(orderEntity.getOrderCnt());
        orderDTO.setOrderDate(orderEntity.getOrderDate());
        orderDTO.setOrderStatus(orderEntity.getOrderStatus());
        orderDTO.setTotalPrice(orderEntity.getTotalPrice());
        orderDTO.setProductSize(orderEntity.getProductSize());
        orderDTO.setAddId(orderEntity.getAddId());
        orderDTO.setProduct(ProductDTO.toProductDTO(orderEntity.getProduct()));
        orderDTO.setUserId(orderEntity.getUserId());

        orderDTO.setProductId(orderEntity.getProduct().getProductId());
        orderDTO.setProductName(orderEntity.getProduct().getProductName());
        orderDTO.setProductPrice(orderEntity.getProduct().getProductPrice());

        DecimalFormat idFormat = new DecimalFormat("000");
        orderDTO.setImageUrl("/static/CrawligData/"+ orderEntity.getProduct().getCateId()+idFormat.format(orderEntity.getProduct().getProductId())+".jpg");

        DecimalFormat commaFormat = new DecimalFormat("#,###,##0");
        orderDTO.setPriceFormat(commaFormat.format(orderEntity.getProduct().getProductPrice()));
        orderDTO.setTotalFormat(commaFormat.format(orderEntity.getTotalPrice()));

        return orderDTO;
    }
}

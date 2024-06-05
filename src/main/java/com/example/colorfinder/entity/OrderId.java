package com.example.colorfinder.entity;


import java.io.Serializable;
import java.util.Objects;

public class OrderId implements Serializable {
    private Long orderId;
    private Long productId;

    // 기본 생성자
    public OrderId() {}

    // 생성자
    public OrderId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    // equals와 hashCode 메서드 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId1 = (OrderId) o;
        return Objects.equals(orderId, orderId1.orderId) && Objects.equals(productId, orderId1.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}


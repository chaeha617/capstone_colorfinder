package com.example.colorfinder.entity;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.dto.OrderDTO;

public class OrderInfo {
    private OrderDTO order;
    private AddressDTO address;

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}

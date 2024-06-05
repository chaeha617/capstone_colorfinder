package com.example.colorfinder.service;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.OrderDTO;
import com.example.colorfinder.entity.AddressEntity;
import com.example.colorfinder.entity.CartEntity;
import com.example.colorfinder.entity.OrderEntity;
import com.example.colorfinder.repository.CartRepository;
import com.example.colorfinder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public List<OrderDTO> findByUserId(Long userId){
        List<OrderEntity> orderEntityList = orderRepository.findByUserId(userId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList){
            orderDTOList.add(OrderDTO.toOrderDTO(orderEntity));
        }
        return orderDTOList;
    }

    public Long saveOrders(OrderDTO orderDTO){
        return orderRepository.save(OrderEntity.toOrderEntity(orderDTO)).getOrderId();
    }

    public Long findMaxAddIdByUserId(Long userId) {
        Long maxAddId = orderRepository.findMaxAddIdByUserId(userId);
        return maxAddId != null ? maxAddId : 0L;
    }

    public void deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}

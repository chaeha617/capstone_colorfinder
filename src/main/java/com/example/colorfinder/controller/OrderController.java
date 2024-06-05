package com.example.colorfinder.controller;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.entity.OrderEntity;
import com.example.colorfinder.entity.OrderInfo;
import com.example.colorfinder.service.AddressService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.OrderDTO;
import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.service.CartService;
import com.example.colorfinder.service.OrderService;
import com.example.colorfinder.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;
    private final CartService cartService;
    private final AddressService addressService;


    @RequestMapping(value = "/cart/pay/saveInfo", method = RequestMethod.POST)
    public String saveCartList(@RequestParam("selectedProductIds") String selectedProductIds,
                               @RequestParam("addressName") String addressName,
                               @RequestParam("postalCode") Integer postalCode,
                               @RequestParam("roadAddress") String roadAddress,
                               @RequestParam("detailAddress") String detailAddress,
                               @RequestParam("phoneNumber") String phoneNumber,
                               Model model) {

        Long userId = null;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = Long.parseLong(authentication.getName());
        }catch (Exception e){
            return "redirect:/login";
        }

        Map<String, String> validationErrors = new HashMap<>();

        if (addressName == null || addressName.trim().isEmpty()) {
            validationErrors.put("addressNameError", "이름은 필수 입력 값입니다.");
        }
        if (postalCode == null) {
            validationErrors.put("postalCodeError", "우편번호는 필수 입력 값입니다.");
        } else if (!postalCode.toString().matches("\\d{5}")) {
            validationErrors.put("postalCodeError", "우편번호는 5자리 숫자여야 합니다.");
        }
        if (roadAddress == null || roadAddress.trim().isEmpty()) {
            validationErrors.put("roadAddressError", "도로명 주소는 필수 입력 값입니다.");
        } else if (!roadAddress.matches("^[가-힣A-Za-z0-9\\s]+$")) {
            validationErrors.put("roadAddressError", "도로명 주소 형식이 맞지 않습니다.");
        }
        if (phoneNumber != null && !phoneNumber.matches("^010\\d{8}$")) {
            validationErrors.put("phoneNumberError", "전화번호 형식이 맞지 않습니다. 예: 01012345678");
        }

        if (!validationErrors.isEmpty()) {
            System.out.println(validationErrors);

            // orderList와 addressDTO 정보를 다시 불러오기
            List<Long> ids = Arrays.stream(selectedProductIds.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<CartDTO> orderList = cartService.findByCartIds(ids);
            AddressDTO addressDTO = addressService.findMinAddIdByUserId(userId);

            model.addAttribute("orderList", orderList);
            model.addAttribute("address", addressDTO);

            model.addAttribute("validationErrors", validationErrors);
            return "payFor"; // Return to the form with errors
        }

        // cartId List를 통해 cartDTO 끌어오기
        List<Long> ids = Arrays.stream(selectedProductIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        List<CartDTO> cartList = cartService.findByCartIds(ids);

        // 기본 주소 값
        AddressDTO existingAddressDTO = addressService.findMinAddIdByUserId(userId);

        // address 테이블에 데이터 저장
        AddressDTO addressDTO = AddressDTO.builder()
                .addName(addressName)
                .addCode(postalCode)
                .address(roadAddress)
                .addDetail(detailAddress)
                .addTel(phoneNumber)
                .userId(userId)
                .build();


        // 폼 내용이 기본 주소랑 같으면 newAddId를 기본 addID로, 다르면 새칼럼 생성
        Long newAddId = (existingAddressDTO.equals(addressDTO)) ? existingAddressDTO.getAddId() : addressService.saveAddress(addressDTO);

        // userId에 저장된 addId 중에 가장 작은 addId 가져오기
        /*addid = addressService.findMinAddIdByUserId(id);*/

        Long orderId = orderService.findMaxAddIdByUserId(userId) + 1;
        // order테이블에 데이터 저장
        for (CartDTO cartDTO : cartList) {
            ProductDTO product = productService.findById(cartDTO.getProductId());

            OrderDTO order = new OrderDTO();
            order.setOrderId(orderId);
            order.setOrderCnt(cartDTO.getCartCnt());
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus("배송전");
            order.setTotalPrice(cartDTO.getTotalPrice());
            order.setProductSize(cartDTO.getProductSize());
            order.setAddId(newAddId); //수정필요
            order.setProduct(product);
            order.setUserId(cartDTO.getUserId());

            order.setProductId(cartDTO.getProductId());
            order.setProductName(cartDTO.getProductName());
            order.setProductPrice(cartDTO.getProductPrice());

            Long orderId2 = orderService.saveOrders(order);
            orderService.deleteCartById(cartDTO.getCartId());
        }

        return "redirect:/cart/pay/saveInfo";  // 주문 완료 후 이동할 페이지
    }

    @RequestMapping(value = "/cart/pay/saveInfo", method = RequestMethod.GET)
    public String getSaveInfo(Model model) {
        Long userId = null;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userId = Long.parseLong(authentication.getName());
        }catch (Exception e){
            return "redirect:/login";
        }

        List<OrderDTO> orderList = orderService.findByUserId(userId);

        // orderId를 기준으로 주문 정보와 배송지 정보를 묶음
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (OrderDTO order : orderList) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder(order);
            orderInfo.setAddress(addressService.findByAddId(order.getAddId()));
            orderInfoList.add(orderInfo);
        }

        model.addAttribute("orderInfoList", orderInfoList);
        return "OrderList";
    }

}

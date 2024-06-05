package com.example.colorfinder.entity;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.dto.CartDTO;
import com.example.colorfinder.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addId;

    @Column(name = "addName", nullable = false)
    private String addName;

    @Column(name = "addCode", nullable = false)
    private Integer addCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "addDetail")
    private String addDetail;

    @Column(name = "addTel", nullable = false)
    private String addTel;

    @Column(nullable = false)
    private Long userId;


    public static  AddressEntity toAddressEntity(AddressDTO addressDTO){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddId(addressDTO.getAddId());
        addressEntity.setAddName(addressDTO.getAddName());
        addressEntity.setAddCode(addressDTO.getAddCode());
        addressEntity.setAddress(addressDTO.getAddress());
        addressEntity.setAddDetail(addressDTO.getAddDetail());
        addressEntity.setAddTel(addressDTO.getAddTel());
        addressEntity.setUserId(addressDTO.getUserId());
        return addressEntity;
    }
}


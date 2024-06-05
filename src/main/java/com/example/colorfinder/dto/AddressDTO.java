package com.example.colorfinder.dto;

import com.example.colorfinder.entity.AddressEntity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private Long addId;

    private String addName;

    private String addCode;

    private String address;

    private String addDetail;

    private String addTel;

    private Long userId;

    private Map<String, String> validationErrors;


    public static AddressDTO toAddressDTO(AddressEntity addressEntity){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddId(addressEntity.getAddId());
        addressDTO.setAddName(addressEntity.getAddName());
        addressDTO.setAddCode(addressEntity.getAddCode());
        addressDTO.setAddress(addressEntity.getAddress());
        addressDTO.setAddDetail(addressEntity.getAddDetail());
        addressDTO.setAddTel(addressEntity.getAddTel());
        addressDTO.setUserId(addressEntity.getUserId());
        addressDTO.setValidationErrors(null);
        return addressDTO;
    }
}


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

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String addName;

    @NotNull(message = "우편번호는 필수 입력 값입니다.")
    @Min(value = 10000, message = "우편번호는 5자리 숫자여야 합니다.")
    @Max(value = 99999, message = "우편번호는 5자리 숫자여야 합니다.")
    private Integer addCode;

    @NotBlank(message = "도로명 주소는 필수 입력 값입니다.")
    @Pattern(regexp = "^[가-힣A-Za-z0-9\\s]+$", message = "도로명 주소 형식이 맞지 않습니다.")
    private String address;

    private String addDetail;

    @Pattern(regexp = "^010\\d{8}$", message = "전화번호 형식이 맞지 않습니다. 예: 01012345678")
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


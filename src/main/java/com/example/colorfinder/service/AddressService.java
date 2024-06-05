package com.example.colorfinder.service;

import com.example.colorfinder.dto.AddressDTO;
import com.example.colorfinder.dto.OrderDTO;
import com.example.colorfinder.entity.AddressEntity;
import com.example.colorfinder.entity.OrderEntity;
import com.example.colorfinder.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO findMinAddIdByUserId(Long userId) {
        Long minAddId = addressRepository.findMinAddIdByUserId(userId);
        if (minAddId != null) {
            AddressEntity addressEntity = addressRepository.findById(minAddId).orElse(null);
            return mapToAddressDTO(addressEntity);
        }
        return null;
    }

    // AddressEntity를 AddressDTO로 매핑하는 메서드
    private AddressDTO mapToAddressDTO(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return AddressDTO.builder()
                .addId(addressEntity.getAddId())
                .addName(addressEntity.getAddName())
                .addCode(addressEntity.getAddCode())
                .address(addressEntity.getAddress())
                .addDetail(addressEntity.getAddDetail())
                .addTel(addressEntity.getAddTel())
                .userId(addressEntity.getUserId())
                .build();
    }

    public AddressDTO findByAddId(Long addId) {
        return mapToAddressDTO(addressRepository.findByAddId(addId));
    }

    public Long saveAddress(AddressDTO addressDTO){
        AddressEntity savedEntity = addressRepository.save(AddressEntity.toAddressEntity(addressDTO));
        return savedEntity.getAddId();
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}


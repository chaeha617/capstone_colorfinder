package com.example.colorfinder.service;

import com.example.colorfinder.dto.ProductDTO;
import com.example.colorfinder.entity.ProductEntity;
import com.example.colorfinder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductDTO> findAll(){
        List<ProductEntity> productEntityList = productRepository.findAll();

        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }

    public ProductDTO findById(Long productId){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
        if (optionalProductEntity.isPresent()) {
            return ProductDTO.toProductDTO(optionalProductEntity.get());
        }else {
            return null;
        }
    }

    public List<ProductDTO> findByCateId(String cateId){
        List<ProductEntity> productEntityList = productRepository.findByCateId(cateId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }
}

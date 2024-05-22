package com.example.colorfinder.entity;

import com.example.colorfinder.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private Integer productPrice;

    @Column(name = "gender")
    private String gender;

    @Column(name = "temp")
    private Integer temp;

    @Column(name = "cateId")
    private String cateId;

    @Column(name = "colorId")
    private String colorId;


    public static ProductEntity toProductEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDTO.getProductId());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setGender(productDTO.getGender());
        productEntity.setTemp(productDTO.getTemp());
        productEntity.setCateId(productDTO.getCateId());
        productEntity.setColorId(productDTO.getColorId());

        return productEntity;
    }

}

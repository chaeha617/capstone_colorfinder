package com.example.colorfinder.dto;

import com.example.colorfinder.entity.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO { //회원 정보를 필드로 정의
    private Long productId;
    private String productName;
    private Integer productPrice;
    private String gender;
    private Integer Temp;
    private String cateId;
    private String colorId;
    private String imageUrl;
    private String priceFormat;


    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

    public static ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productEntity.getProductId());
        productDTO.setProductName(productEntity.getProductName());

        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setGender(productEntity.getGender());
        productDTO.setTemp(productEntity.getTemp());
        productDTO.setCateId(productEntity.getCateId());
        productDTO.setColorId(productEntity.getColorId());

        DecimalFormat idFormat = new DecimalFormat("000");
        productDTO.setImageUrl("/static/CrawligData/"+ productEntity.getCateId()+idFormat.format(productEntity.getProductId())+".jpg");

        DecimalFormat commaFormat = new DecimalFormat("#,###,##0");
        productDTO.setPriceFormat(commaFormat.format(productEntity.getProductPrice()));



        return productDTO;
    }
}
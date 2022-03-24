package com.kh.product.domain.dao;

import lombok.*;

@Data

@NoArgsConstructor
public class Product {

    private long productId;        //상품아이디
    private String productName;     //상품이름
    private long productQuantity;    //상품수량
    private long productPrice;       //상품가격

}

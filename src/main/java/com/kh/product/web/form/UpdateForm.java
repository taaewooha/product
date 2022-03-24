package com.kh.product.web.form;

import lombok.Data;

@Data
public class UpdateForm {

    private Long productId;

    private String productName;

    private Long productQuantity;

    private Long productPrice;
}

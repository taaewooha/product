package com.kh.product.domain.svc;

import com.kh.product.domain.dao.Product;

import java.util.List;

public interface ProductSVC {

    //등록
    Product save(Product product);

    //조회
    Product findById(long productId);

    //수정
    Product update(Long productId, Product product);

    //삭제
    Product delete(Long productId);

    //전체조회
    List<Product> findAll();
}

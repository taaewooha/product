package com.kh.product.domain.dao;

import java.util.List;

public interface ProductDAO {

    //등록
    Product save(Product product);

    //조회
    Product findById(long productId);

    //수정
    Product update(long productId, Product product);

    //삭제
    Product delete(long productId);

    //전체조회
    List<Product> findAll();

}

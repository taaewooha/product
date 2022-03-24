package com.kh.product.domain.svc;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductSVCImpl implements ProductSVC{

    private final ProductDAO productDAO;

    //등록
    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    //조회
    @Override
    public Product findById(long productId) {
        Product findedProduct = productDAO.findById(productId);
        return findedProduct;
    }

    //수정
    @Override
    public Product update(Long productId, Product product) {
        return productDAO.update(productId, product);
    }

    //삭제
    @Override
    public Product delete(Long productId) {
        return productDAO.delete(productId);
    }

    //전체조회
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }
}

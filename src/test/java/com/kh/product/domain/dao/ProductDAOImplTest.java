package com.kh.product.domain.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    @DisplayName("등록")
    void save() {
        Product product = new Product();

        product.setProductName("초코우유");
        product.setProductQuantity(1000);
        product.setProductPrice(1300);

        Product save = productDAO.save(product);
        Assertions.assertThat(save.getProductName()).isEqualTo("초코우유");
        log.info("save={}", save);

    }

    @Test
    @DisplayName("조회")
    void findById() {
        Product product = productDAO.findById(36);
        log.info("findById={}", product);
    }

    @Test
    @DisplayName("수정")
    void update() {
        Long productId = 23L;
        //수정전
        Product beforeUpdatingItem = productDAO.findById(productId);
        beforeUpdatingItem.setProductName("바나나맛우유");
        beforeUpdatingItem.setProductQuantity(2000);
        beforeUpdatingItem.setProductPrice(1600);
        productDAO.update(productId, beforeUpdatingItem);

        //수정후
        Product afterUpdatingItem = productDAO.findById(productId);

        //수정전후 비교
        Assertions.assertThat(beforeUpdatingItem.getProductName())
                .isEqualTo(afterUpdatingItem.getProductName());
        Assertions.assertThat(beforeUpdatingItem.getProductQuantity())
                .isEqualTo(afterUpdatingItem.getProductQuantity());
        Assertions.assertThat(beforeUpdatingItem.getProductPrice())
                .isEqualTo(afterUpdatingItem.getProductPrice());
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        Long productId = 21L;
        Product deleteProduct = productDAO.delete(productId);

        Assertions.assertThat(productDAO.findById(productId)).isNull();

    }

    @Test
    @DisplayName("전체조회")
    void findAll() {
        List<Product> list = productDAO.findAll();

        Assertions.assertThat(list.size()).isEqualTo(25);
//        Assertions.assertThat(list.get(2).getProductName()).isEqualTo("초코우유");
        for (Product product : list) {
            log.info(product.toString());

        }
    }
}
package com.kh.product.domain.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

    private final JdbcTemplate jdbcTemplate;

    //등록
    @Override
    public Product save(Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into product (product_id,product_name,product_quantity,product_price) ");
        sql.append("values(product_product_id_seq.nextval,?,?,?) ");


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"product_id"});
                pstmt.setString(1,product.getProductName());
                pstmt.setLong(2, product.getProductQuantity());
                pstmt.setLong(3, product.getProductPrice());

                return pstmt;
            }
        },keyHolder);

        return findById(Long.valueOf(keyHolder.getKeys().get("product_id").toString()));
    }


    //조회
    @Override
    public Product findById(long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT  ");
        sql.append("  product_id, ");
        sql.append("  product_name,  ");
        sql.append("  product_quantity,  ");
        sql.append("  product_price  ");
        sql.append("FROM  ");
        sql.append("  product ");
        sql.append("where product_id = ?  ");

        Product product = null;
        try {
            product = jdbcTemplate.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Product.class),
                    id);
        }catch (Exception e){ // 1건을 못찾으면
            product = null;
        }

        return product;
    }

    //수정
    @Override
    public Product update(long productId, Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE product ");
        sql.append("   SET product_name = ?, ");
        sql.append("       product_quantity = ?, ");
        sql.append("       product_price = ? ");
        sql.append(" WHERE product_id = ? ");

        int affectedRow = jdbcTemplate.update(
                sql.toString(),
                product.getProductName(),
                product.getProductQuantity(),
                product.getProductPrice(),
                productId
        );

        log.info("Product update(long productId, Product product) affectedRow={}",affectedRow);
        return findById(productId);
    }

    //삭제
    @Override
    public Product delete(long productId) {

        Product product = findById(productId);

        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM product ");
        sql.append(" WHERE product_id = ? ");

        jdbcTemplate.update(sql.toString(), productId);

        return product;
    }

    //전체조회
    @Override
    public List<Product> findAll() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  product_id, ");
        sql.append("  product_name, ");
        sql.append("  product_quantity, ");
        sql.append("  product_price ");
        sql.append("FROM ");
        sql.append("  product ");
        sql.append("Order by product_id desc ");

        List<Product> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));

        return list;
    }
}

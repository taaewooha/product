package com.kh.product.web;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ApiProductController {

    private final ProductSVC productSVC;

    //상품등록
    @ResponseBody
    @PostMapping
    public ApiResult<Product> save(@RequestBody Product product){

        Product savedProduct = productSVC.save(product);

        ApiResult<Product> result = new ApiResult<>("00", "success", savedProduct);
        return result;
    }

    //상품조회
    @ResponseBody
    @GetMapping("/{productId}")
    public ApiResult<Product> findById(@PathVariable Long productId){
        Product findedProduct = productSVC.findById(productId);

        ApiResult<Product> result = null;
        if(findedProduct != null) {
            result = new ApiResult<Product>("00","success",findedProduct);
        }else{
            result = new ApiResult<Product>("99", "fail", null);
        }
        return result;
    }


    //상품수정
    @ResponseBody
    @PatchMapping("/{productId}")
    public ApiResult<String> update(@PathVariable Long productId, @RequestBody Product product){

        log.info("product={}", product);
        Product updateProduct = productSVC.update(productId, product);

        ApiResult result = null;
        if(updateProduct != null) {
            result = new ApiResult<>("00", "success", updateProduct);
        }else{                                  //productDAO.findById(product.getProductId())
            result = new ApiResult<>("99", "fail", "수정할 상품의 아이디가 존재하지 않습니다.");
        }
        return result;
    }


    //상품삭제
    @ResponseBody
    @DeleteMapping("/{productId}")
    public ApiResult<String> delete(@PathVariable Long productId){
        Product deletedProduct = productSVC.delete(productId);

        ApiResult<String> result = null;
        if(deletedProduct != null){
            result = new ApiResult<>("00","succces",deletedProduct.getProductName()+"가 삭제되었습니다");
        }else{
            result = new ApiResult<>("99","fail", "삭제할 상품의 아이디가 존재하지 않습니다.");
        }
        return result;
    }


    //전체조회
    @ResponseBody
    @GetMapping
    public ApiResult<List<Product>> findAll(){

        List<Product> list = productSVC.findAll();
        ApiResult<List<Product>> result = null;
        if(list.size() > 0) {
            result = new ApiResult<>("00","success",list);
        }else{
            result = new ApiResult<>("02","success",null);
        }
        return result;
    }

}

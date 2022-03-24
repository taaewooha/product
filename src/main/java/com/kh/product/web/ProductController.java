package com.kh.product.web;


import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.form.DetailForm;
import com.kh.product.web.form.SaveForm;
import com.kh.product.web.form.UpdateForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductSVC productSVC;

    //등록양식
    @GetMapping
    public String saveForm(Model model) {
        model.addAttribute("saveForm", new SaveForm());
        return "product/saveForm";
    }

    //등록
    @PostMapping
    public String save(@Valid @ModelAttribute SaveForm saveForm,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            return "product/saveForm";
        }
        Product product = new Product();
        BeanUtils.copyProperties(saveForm, product);
        Long productId = productSVC.save(product).getProductId();
        log.info("productId={}",productId);

        redirectAttributes.addAttribute("productId",productId);
        return "redirect:/product/{productId}/detail";
        }

        //목록
        @GetMapping("/all")
        public String findAll(Model model){
            List<Product> all = productSVC.findAll();
            model.addAttribute("list", all);
            return "product/all";
        }

        //조회
        @GetMapping("{productId}/detail")
        public String findByProductId(@PathVariable("productId") Long productId, Model model){
            Product findedProduct = productSVC.findById(productId);
            DetailForm detailForm = new DetailForm();
            BeanUtils.copyProperties(findedProduct, detailForm);

            model.addAttribute("detailForm", detailForm);
            return "product/detailForm";
        }

        //수정양식
        @GetMapping("/{productId}/edit")
        public String updateForm(@PathVariable("productId") Long productId, Model model){
            Product findedProduct = productSVC.findById(productId);
            UpdateForm updateForm = new UpdateForm();
            BeanUtils.copyProperties(findedProduct, updateForm);

            model.addAttribute("updateForm", updateForm);

        return "product/updateForm";
        }

        //수정
        @PostMapping("/{productId}/edit")
    public String update(@PathVariable("productId") Long productId,
                         @Valid @ModelAttribute UpdateForm updateForm,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){

            return "product/updateForm";
        }
        Product product = new Product();
        BeanUtils.copyProperties(updateForm, product);
        productSVC.update(productId, product);

        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/product/{productId}/detail";
        }

        //삭제
        @GetMapping("/{productId}/del")
        public String deleteById(@PathVariable("productId") Long productId){

            productSVC.delete(productId);

            return "redirect:/product/all";
        }
}

package com.quoctan.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.quoctan.pojos.Product;
import com.quoctan.service.ProductService;
import com.quoctan.validator.ProductNameValidator;
import com.quoctan.validator.WebAppValidator;
import java.io.IOException;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private WebAppValidator productValidator;
    @Autowired
    private ProductService productService;
    
    // Khai báo cấu hình xử lý spring validate, nhưng sẽ không sử dụng được bean
    // validate nữa
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(productValidator);
    }

    @GetMapping("/admin/products")
    public String list(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/admin/products")
    public String add(Model model, @ModelAttribute(value = "product") @Valid Product product,
            BindingResult result) {
        if (!result.hasErrors()) {
            if (this.productService.addOrUpdate(product) == true)
                return "redirect:/";
            else
                model.addAttribute("errMsg", "Something wrong!");
        }
        return "product";
    }
}

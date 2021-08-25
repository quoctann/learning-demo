package com.quoctan.controllers;

import com.quoctan.pojos.Cart;
import com.quoctan.pojos.UserDemo;
import com.quoctan.service.CategoryService;
import com.quoctan.service.ProductService;
import com.quoctan.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ControllerAdvice
public class HomeController {
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    
    // Cùng với @ControllerAdvice để đính kèm thông tin chung lên tất cả rq 
    @ModelAttribute
    public void commonAttr(Model model, HttpSession session) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) session.getAttribute("cart")));
    }
    
    // Xử lý get thông thường kết hợp query params/request params
    // Mặc định nếu như có reques param thì nó bắt buộc phải truyền thì mới render,
    // tắt đi bằng cách bật cờ required, default để ko có truyền thì cũng có giá trị
    @RequestMapping("/")
    @Transactional
//    public String index(Model model,
//            @RequestParam(value = "kw", required = false, defaultValue = "") String kw) {
//        model.addAttribute("products", this.productService.getProducts(kw));
//        return "index";
//    }
    public String index(Model model,
            @RequestParam(required = false) Map<String, String> params) {
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("products", this.productService.getProducts(params.getOrDefault("kw", ""), page));
        model.addAttribute("counter", this.productService.countProduct());
        return "index";
    }
    
    @RequestMapping(path = "/cart")
    public String cart() {
        return "cart";
    }
}

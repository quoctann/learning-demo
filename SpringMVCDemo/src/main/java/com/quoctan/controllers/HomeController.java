package com.quoctan.controllers;

import com.quoctan.pojos.UserDemo;
import com.quoctan.service.CategoryService;
import com.quoctan.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    // Xử lý get thông thường kết hợp query params/request params
    // Mặc định nếu như có reques param thì nó bắt buộc phải truyền thì mới render,
    // tắt đi bằng cách bật cờ required, default để ko có truyền thì cũng có giá trị
    @RequestMapping("/")
    public String index(Model model,
            @RequestParam(value = "first_name", required = false, defaultValue = "A") String firstName,
            @RequestParam(value = "last_name", required = false, defaultValue = "B") String lastName) {
        if (firstName != null && lastName != null)
            model.addAttribute("name", String.format("%s %s", firstName, lastName));
        else
            model.addAttribute("name", "Quoc Tan");
        model.addAttribute("userDemo", new UserDemo());
        List<UserDemo> users = new ArrayList<>();
        users.add(new UserDemo("Nam", "Nguyen"));
        users.add(new UserDemo("Binh", "Le"));
        model.addAttribute("users", users);
        return "index";
    }
    
    // Truyền dữ liệu path params
    @RequestMapping("/hello/{name}")
    public String hello(Model model, 
            @PathVariable(value = "name") String name) {
        model.addAttribute("message", "Welcome " + name + "!!!");
        return "hello";
    }
    
    // Khi có nhiều request param thì làm bằng map cho gọn
    @RequestMapping("/toomuch")
    public String tooMuch(Model model,
            @RequestParam Map<String, String> params) {
        String firstName = params.get("first_name");
        String lastName = params.get("last_name");
        if (firstName != null && lastName != null)
            model.addAttribute("name", String.format("%s %s", firstName, lastName));
        else
            model.addAttribute("name", "Quoc Tan");
        return "index";
    }
    
    @RequestMapping(path = "/hello-post", method = RequestMethod.POST)
    public String show(Model model, @ModelAttribute(value = "userDemo") UserDemo userDemo ) {
        model.addAttribute("fullName", userDemo.getFirstName() + " " + userDemo.getLastName());
        return "index";
    }
    
    @RequestMapping(path="/test")
    public String testRedirect(Model model) {
        model.addAttribute("testMsg", "Welcome redirect");
        // Chuyển bằng forward thì nó sẽ lại dữ liệu request cũ, chỉ đổi view
        // chứ không thay đổi url, sử dụng url là tạo mới một request luôn mất
        // toàn bộ dữ liệu cũ. forward dùng để chuyển action này sang action
        // khác
        return "forward:/hello/RedirectName";
    }
//    // Sử dụng hibernate truy vấn dữ liệu
//    @Autowired
//    private LocalSessionFactoryBean sessionFactory;
//    
//    @RequestMapping(path="hibernate-demo")
//    @Transactional
//    public String integratedDemo(Model model) {
////        // Cách làm không sử dụng connection pool
////        Session s = sessionFactory.getObject().openSession();
////        Query q = s.createQuery("FROM Category");
////        model.addAttribute("categories", q.getResultList());
////        s.close();
//
//        Session s = sessionFactory.getObject().getCurrentSession();
//        Query q = s.createQuery("From Category");
//        model.addAttribute("categories", q.getResultList());
//        return "query";
//    }
    
    
    
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
        
    @RequestMapping("/demo2")
    public String demo2(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("products", this.productService.getProducts(""));
        //return "query";
        // index lúc này sẽ vào tiles config rà xem trùng name nào thì nó xử lý,
        // nếu ko có thì nó mởi truy xuất tập tin theo local view resolver
        return "index";
    }
}

package com.quoctan.controllers;

import com.quoctan.pojos.UserDemo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
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
}

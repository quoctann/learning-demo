package com.quoctan.controllers;

import com.quoctan.pojos.User;
import com.quoctan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    @Autowired
    private UserService userDetailsService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "user") User user) {
        // Làm nhanh pass voi confirm thôi, khi làm lại phải để trong validate
        String errMsg = "";
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (this.userDetailsService.addUser(user) == true) {
                return "redirect:/login";
            } else {
                errMsg = "Something wrong data type";
            }
        } else {
            errMsg = "Retype password does not matched!";
        }
        model.addAttribute("errMsg", errMsg);
        return "register";
    }
}

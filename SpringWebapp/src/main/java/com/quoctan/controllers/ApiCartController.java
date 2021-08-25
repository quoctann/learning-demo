package com.quoctan.controllers;

import com.quoctan.utils.Utils;
import com.quoctan.pojos.Cart;
import com.quoctan.pojos.Product;
import com.quoctan.service.ProductService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiCartController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = this.productService.getProducts("", 1);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/api/cart/{productId}")
    public ResponseEntity<Integer> cart(@PathVariable(value = "productId") Integer productId,
            HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null)
            cart = new HashMap<>();
        
        if (cart.containsKey(productId) == true) {
            // Sản phẩm đã có trong giỏ rồi
            Cart c = cart.get(productId);
            c.setCount(c.getCount() + 1);
        } else {
            // Sản phẩm chưa có trong giỏ
            Product p = this.productService.getProductById(productId);
            Cart c = new Cart();
            c.setProductId(p.getId());
            c.setName(p.getName());
            c.setPrice(p.getPrice());
            c.setCount(1);
            cart.put(productId, c);
        }
        
        session.setAttribute("cart", cart);
        //System.out.println("CART" + Utils.countCart(cart));
        return new ResponseEntity<>(Utils.countCart(cart), HttpStatus.OK);
    }
}

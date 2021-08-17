package com.quoctan.service;

import com.quoctan.pojos.Product;
import java.util.List;


public interface ProductService {
    List<Product> getProducts(String kw);
}

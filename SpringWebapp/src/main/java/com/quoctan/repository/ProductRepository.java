package com.quoctan.repository;

import com.quoctan.pojos.Product;
import java.util.List;


public interface ProductRepository {
    List<Product> getProducts(String kw);
}

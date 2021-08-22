package com.quoctan.repository;

import com.quoctan.pojos.Product;
import java.util.List;


public interface ProductRepository {
    List<Product> getProducts(String kw, int page);
    long countProduct();
    boolean addOrUpdate(Product product);
}

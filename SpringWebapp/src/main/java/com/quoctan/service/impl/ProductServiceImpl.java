package com.quoctan.service.impl;

import com.quoctan.pojos.Product;
import com.quoctan.repository.ProductRepository;
import com.quoctan.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> getProducts(String kw) {
        return this.productRepository.getProducts(kw);
    }
    
}

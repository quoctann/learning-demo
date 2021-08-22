package com.quoctan.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.quoctan.pojos.Product;
import com.quoctan.repository.ProductRepository;
import com.quoctan.service.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String kw, int page) {
        return this.productRepository.getProducts(kw, page);
    }

    @Override
    public boolean addOrUpdate(Product product) {
        try {
            Map r = this.cloudinary.uploader().upload(product.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            product.setImage((String) r.get("secure_url"));
            return this.productRepository.addOrUpdate(product);
        } catch (IOException ex) {
            System.err.println("Add product" + ex.getMessage());
        }
        return false;
    }

    @Override
    public long countProduct() {
        return this.productRepository.countProduct();
    }

}

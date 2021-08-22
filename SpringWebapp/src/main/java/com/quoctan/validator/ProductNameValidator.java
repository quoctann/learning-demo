package com.quoctan.validator;

import com.quoctan.pojos.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ProductNameValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product p = (Product) target;
        if (!p.getName().contains("quoctan")) {
            errors.rejectValue("name", "product.name.validate.err");
        }
    }
    
}

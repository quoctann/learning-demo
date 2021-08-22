package com.quoctan.validator;

import com.quoctan.pojos.Product;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WebAppValidator implements Validator {
    @Autowired
    private javax.validation.Validator beanValidator;
    private Set<Validator> springValidators;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Duyệt qua bean validate
        Set<ConstraintViolation<Object>> beans = this.beanValidator.validate(target);
        for (ConstraintViolation<Object> obj: beans) {
            errors.rejectValue(obj.getPropertyPath().toString(),
                    obj.getMessageTemplate(),
                    obj.getMessage());
        }
        
        // Duyệt qua spring validate tự mình custom
        for (Validator v: this.springValidators) {
            v.validate(target, errors);
        }
    }

    public void setSpringValidator(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
}

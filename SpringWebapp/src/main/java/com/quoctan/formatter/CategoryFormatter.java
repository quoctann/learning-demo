package com.quoctan.formatter;

import com.quoctan.pojos.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


public class CategoryFormatter implements Formatter<Category>{

    @Override
    public String print(Category object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Category parse(String cateId, Locale locale) throws ParseException {
        Category c = new Category();
        c.setId(Integer.parseInt(cateId));
        return c;
    }
    
}

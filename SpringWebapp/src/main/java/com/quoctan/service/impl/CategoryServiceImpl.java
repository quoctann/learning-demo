package com.quoctan.service.impl;

import com.quoctan.pojos.Category;
import com.quoctan.repository.CategoryRepository;
import com.quoctan.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
            
    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.getCategories();
    }
    
}

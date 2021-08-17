// Package repository hay domain là tập các lớp interface xử lý logic với csdl
package com.quoctan.repository;

import com.quoctan.pojos.Category;
import java.util.List;


public interface CategoryRepository {
    List<Category> getCategories();
}

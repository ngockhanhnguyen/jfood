package com.kadajko.product.domain.service;

import java.util.List;
import java.util.UUID;

import com.kadajko.product.domain.model.Category;

public interface CategoryService {
    List<Category> getAll();
    
    Category get(UUID id);
    
    Category add(Category entity);
    
    Category update(Category entity);

    void remove(UUID id);
    
    List<Category> getCategoryByName(String name);
}

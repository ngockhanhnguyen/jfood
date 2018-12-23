package com.kadajko.product.domain.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadajko.product.domain.model.Category;
import com.kadajko.product.domain.repository.CategoryRepository;
import com.kadajko.product.domain.service.CategoryService;
import com.kadajko.product.exception.UnknownResourceException;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        List<Category> categories = repository.getAll();
//        return categories.stream()
//                .filter(category -> category.getParentId() == null)
//                .collect(Collectors.toList());
        
        return categories;
    }

    @Override
    public Category get(UUID id) throws UnknownResourceException {
        Category category = repository.get(id);
        if (category == null)
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + id);
        return category;
    }

    @Override
    public Category add(Category entity) {
        return repository.add(entity);
    }

    @Override
    public Category update(Category entity) throws UnknownResourceException {
        Category category = null;
        try {
            category = repository.update(entity);
        } catch (Exception e) {
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + entity.getId());
        }
        
        return category;
    }

    @Override
    public void remove(UUID id) throws UnknownResourceException {
        try {
            repository.remove(id);
        } catch (Exception e) {
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + id);
        }
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return repository.getCategoryByName(name);
    }

}

package com.kadajko.product.domain.service;

import java.util.List;
import java.util.UUID;

import com.kadajko.product.domain.model.Product;

public interface ProductService {
    List<Product> getAll();
    
    Product get(UUID id);
    
    List<Product> getByPage(int size, int page);
    
    List<Product> getByCategory(UUID id);
    
    List<Product> getByCategoryAndPage(UUID id, int size, int page);
    
    List<Product> getByName(String key);
    
    List<Product> getByNameAndPage(String key, int size, int page);
    
    Product add(Product entity);
    
    Product update(Product entity);

    void remove(UUID id);
}

package com.kadajko.product.domain.repository;

import java.util.List;
import java.util.UUID;

import com.kadajko.product.domain.model.Product;

public interface ProductRepository extends BaseRepository<Product, UUID> {
    
    List<Product> getByPage(int size, int page);
    
    List<Product> getByCategory(UUID id);
    
    List<Product> getByCategoryAndPage(UUID id, int size, int page);
    
    List<Product> getByName(String key);
    
    List<Product> getByNameAndPage(String key, int size, int page);
}

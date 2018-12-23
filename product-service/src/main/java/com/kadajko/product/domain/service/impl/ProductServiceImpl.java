package com.kadajko.product.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadajko.product.domain.model.Product;
import com.kadajko.product.domain.repository.ProductRepository;
import com.kadajko.product.domain.service.ProductService;
import com.kadajko.product.exception.UnknownResourceException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public Product get(UUID id) throws UnknownResourceException {
        Product product = repository.get(id);
        if (product == null)
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + id);
        return product;
    }

    @Override
    public Product add(Product entity) {
        entity.setCreatedAt(new Date());
        return repository.add(entity);
    }

    @Override
    public Product update(Product entity) throws UnknownResourceException {
        try {
            return repository.update(entity);
        } catch (Exception e) {
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + entity.getId());
        }
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
    public List<Product> getByPage(int size, int page) {
        return repository.getByPage(size, page);
    }

    @Override
    public List<Product> getByCategory(UUID id) {
        return repository.getByCategory(id);
    }

    @Override
    public List<Product> getByCategoryAndPage(UUID id, int size, int page) {
        return repository.getByCategoryAndPage(id, size, page);
    }

    @Override
    public List<Product> getByName(String key) {
        return repository.getByName(key);
    }

    @Override
    public List<Product> getByNameAndPage(String key, int size, int page) {
        return repository.getByNameAndPage(key, size, page);
    }

}

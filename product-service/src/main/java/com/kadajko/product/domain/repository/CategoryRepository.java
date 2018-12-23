package com.kadajko.product.domain.repository;

import java.util.List;
import java.util.UUID;

import com.kadajko.product.domain.model.Category;

public interface CategoryRepository extends BaseRepository<Category, UUID> {
    List<Category> getCategoryByName(String name);
}

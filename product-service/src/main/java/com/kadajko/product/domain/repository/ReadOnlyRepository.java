package com.kadajko.product.domain.repository;

import java.util.List;

public interface ReadOnlyRepository<T, PK> {
	boolean contains(PK id);
	T get(PK id);
	List<T> getAll();
}

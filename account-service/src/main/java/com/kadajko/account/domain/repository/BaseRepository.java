package com.kadajko.account.domain.repository;

public interface BaseRepository<T, PK> extends ReadOnlyRepository<T, PK> {
	T add(T entity);
	void remove(PK id);
	T update(T entity);
}

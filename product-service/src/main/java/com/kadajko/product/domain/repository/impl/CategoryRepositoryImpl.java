package com.kadajko.product.domain.repository.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kadajko.product.domain.model.Category;
import com.kadajko.product.domain.repository.CategoryRepository;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Category add(Category entity) {
	    Session session = getSession();
		return session.get(Category.class, session.save(entity));
	}

	public void remove(UUID id) {
	    Session session = getSession();
	    session.delete(session.load(Category.class, id));
	}

	public Category update(Category entity) {
	    getSession().update(entity);
	    return entity;
	}

	public boolean contains(UUID id) {
	    throw new UnsupportedOperationException("Not supported yet.");
	}

	public Category get(UUID id) {
		return getSession().get(Category.class, id);
	}

	public List<Category> getAll() {
		List<Category> categories = 
			getSession().createCriteria(Category.class)
			.list();
		
//		categories.stream().forEach(category -> Hibernate.initialize(category.getProducts()));
		
		return categories;
	}

    @Override
    public List<Category> getCategoryByName(String name) {
        return getSession().createCriteria(Category.class)
            .add(Restrictions.like("title", "%" + name + "%"))
            .list();
    }
}

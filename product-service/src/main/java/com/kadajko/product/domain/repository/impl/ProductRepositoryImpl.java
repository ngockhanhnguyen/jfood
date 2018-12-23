package com.kadajko.product.domain.repository.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kadajko.product.domain.model.Product;
import com.kadajko.product.domain.repository.ProductRepository;
import com.kadajko.product.exception.UnknownResourceException;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public Product add(Product entity) {
        Session session = getSession();
        return session.get(Product.class, session.save(entity));
    }

    @Override
    public void remove(UUID id) throws UnknownResourceException {
        Session session = getSession();
        session.delete(session.load(Product.class, id));
    }

    @Override
    public Product update(Product entity) throws UnknownResourceException {
        Session session = getSession();
        Product product = session.get(Product.class, entity.getId());
        if (product == null)
            throw new UnknownResourceException(
                    "Does not exist Category with id: " + entity.getId());
        
        /* Form update phải đầy đủ thuộc tính, 
         * nếu không hậu quả sẽ khôn lường */
        BeanUtils.copyProperties(entity, product, 
                new String[] {"id", "createdAt", "images"});
        session.update(product);
        return product;
    }

    @Override
    public boolean contains(UUID id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Product get(UUID id) throws UnknownResourceException {
        Product product = getSession().get(Product.class, id);
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = 
                getSession().createCriteria(Product.class).list();
        
        return products;
    }

    @Override
    public List<Product> getByPage(int size, int page) {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.setFirstResult(size * (page - 1));
        criteria.setMaxResults(size);
        return criteria.list();
    }

    @Override
    public List<Product> getByCategory(UUID id) {
        return getSession().createCriteria(Product.class)
                .add(Restrictions.eq("categoryId", id))
                .list();
    }

    @Override
    public List<Product> getByCategoryAndPage(UUID id, int size, int page) {
        Criteria criteria = getSession().createCriteria(Product.class)
                .add(Restrictions.eq("categoryId", id));
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

    @Override
    public List<Product> getByName(String key) {
        return getSession().createCriteria(Product.class)
                .add(Restrictions.like("name", "%" + key + "%")).list();
    }

    @Override
    public List<Product> getByNameAndPage(String key, int size, int page) {
        Criteria criteria = getSession().createCriteria(Product.class)
                .add(Restrictions.like("name", "%" + key + "%"));
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

}

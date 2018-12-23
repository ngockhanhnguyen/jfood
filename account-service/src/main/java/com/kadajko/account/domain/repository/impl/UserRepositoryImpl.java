package com.kadajko.account.domain.repository.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kadajko.account.domain.model.User;
import com.kadajko.account.domain.repository.UserRepository;
import com.kadajko.account.exception.EmailExistsException;
import com.kadajko.account.exception.EmailNotExistsException;
import com.kadajko.account.exception.UnknownResourceException;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    Logger logger = Logger.getLogger(UserRepository.class.toString());

    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User add(User entity) {
        Session session = getSession();
        return session.get(User.class, session.save(entity));
    }

    @Override
    public void remove(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User update(User entity) {
        Session session = getSession();
        User user = this.loadUserByEmail(entity.getEmail());
        if (user == null)
            throw new UnknownResourceException(
                    "User with email '" + entity.getEmail() 
                    + "' is not exist.");
        BeanUtils.copyProperties(entity, user, 
                new String[] {"id", "createdAt", "password"});
        session.update(user);
        return user;
    }

    @Override
    public boolean contains(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User get(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getAll() {
       return getSession().createCriteria(User.class).list();
    }

    @Override
    public User loadUserByEmail(String email) {
        logger.warning("reached loadUserByEmail");
        User user = (User) getSession()
                .createCriteria(User.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        
        logger.warning("reached end of loadUserByEmail");

        return user;
    }

    @Override
    public boolean containsEmail(String email) {
        User user = (User) getSession()
                .createCriteria(User.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        
        return user != null;
    }

    @Override
    public void changePassword(String email, String newPassword) {
        Session session = getSession();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
        if (user == null)
            throw new UnknownResourceException(
                    "User with email '" + email + "' is not exist.");
        user.setPassword(newPassword);
        session.save(user);
    }

}

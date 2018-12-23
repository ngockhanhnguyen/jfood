package com.kadajko.account.domain.repository.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kadajko.account.domain.model.Role;
import com.kadajko.account.domain.model.RoleName;
import com.kadajko.account.domain.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean contains(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Role get(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Role> getAll() {
        return getSession().createCriteria(Role.class).list();
    }

    @Override
    public Role add(Role entity) {
        Session session = getSession();
        return session.get(Role.class, session.save(entity));
    }

    @Override
    public void remove(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Role update(Role entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role getRoleByRoleName(RoleName name) {
        return (Role) getSession()
                .createCriteria(Role.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}

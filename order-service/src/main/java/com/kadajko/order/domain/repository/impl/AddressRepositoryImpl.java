package com.kadajko.order.domain.repository.impl;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kadajko.order.domain.model.Address;
import com.kadajko.order.domain.repository.AddressRepositoryCustom;

@Repository
public class AddressRepositoryImpl implements AddressRepositoryCustom {
	@Autowired
	EntityManager entityManager;

	@Override
	public Address findAddressByOrderId(UUID id) {
		Query query = entityManager.createNativeQuery("select * from address where order_id = :order_id");
		query.setParameter("order_id", id);
		return (Address) query.getSingleResult();
	}
	
	
}

package com.kadajko.order.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.kadajko.order.domain.model.Address;

public interface AddressRepository extends CrudRepository<Address, UUID> {
	
}

package com.kadajko.order.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.kadajko.order.domain.model.Order;

public interface OrderRepository extends CrudRepository<Order, UUID> {

}

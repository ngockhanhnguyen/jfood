package com.kadajko.order.domain.service;

import java.util.Collection;
import java.util.UUID;

import com.kadajko.order.domain.model.Address;
import com.kadajko.order.domain.model.Order;
import com.kadajko.order.domain.model.OrderItem;
import com.kadajko.order.domain.model.OrderStatus;

public interface OrderService {
	Collection<Order> getAll();
	Order findOne(UUID id);
	Collection<OrderItem> getOrderItems(UUID orderId);
	Address addAddress(UUID orderId, Address addr);
	void updateStatus(UUID orderId, OrderStatus status);
	void delete(UUID id);
	Order save(Order order);
}

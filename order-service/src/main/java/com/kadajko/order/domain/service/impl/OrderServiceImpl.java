package com.kadajko.order.domain.service.impl;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadajko.order.domain.model.Address;
import com.kadajko.order.domain.model.Order;
import com.kadajko.order.domain.model.OrderItem;
import com.kadajko.order.domain.model.OrderStatus;
import com.kadajko.order.domain.repository.AddressRepository;
import com.kadajko.order.domain.repository.OrderRepository;
import com.kadajko.order.domain.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Collection<Order> getAll() {
		return (Collection<Order>) orderRepository.findAll();
	}

	@Override
	public Order findOne(UUID id) {
		return orderRepository.findOne(id);
	}

	@Override
	public Collection<OrderItem> getOrderItems(UUID orderId) {
		return orderRepository.findOne(orderId).getItems();
	}

	@Override
	public Address addAddress(UUID orderId, Address addr) {
		addr.setOrder(orderRepository.findOne(orderId));
		addressRepository.save(addr);
		return addr;
	}

	@Override
	public void updateStatus(UUID orderId, OrderStatus status) {
		Order order = orderRepository.findOne(orderId);
		order.setStatus(status);;
		orderRepository.save(order);
	}

	@Override
	public void delete(UUID id) {
		orderRepository.delete(id);
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

}

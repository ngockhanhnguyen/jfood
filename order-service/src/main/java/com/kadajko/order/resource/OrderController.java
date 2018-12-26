package com.kadajko.order.resource;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kadajko.order.domain.model.Address;
import com.kadajko.order.domain.model.Order;
import com.kadajko.order.domain.model.OrderItem;
import com.kadajko.order.domain.model.OrderStatus;
import com.kadajko.order.domain.repository.AddressRepository;
import com.kadajko.order.domain.repository.AddressRepositoryCustom;
import com.kadajko.order.domain.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setCreatedAt(new Date());
        order.setStatus(OrderStatus.CHO_XU_LY);
        
        Order newOrder = orderService.save(order);
        Address addr = order.getAddress();
        addr.setOrder(newOrder);
        addressRepository.save(addr);
//        order.setAddress(addr);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/order/")
                .path(order.getId().toString())
                .build()
                .toUri());
        
        return new ResponseEntity<Order>(
                order, headers, HttpStatus.CREATED);
    }
	
	@GetMapping
	public Collection<Order> getAll() {
		return orderService.getAll();
	}
	
	@GetMapping("{id}/items")
	public Collection<OrderItem> getItems(@PathVariable UUID id) {
		return orderService.getOrderItems(id);
	}
	
	@GetMapping("{id}/address")
	public Address getAddress(@PathVariable UUID id) {
		return addressRepositoryCustom.findAddressByOrderId(id);
	}
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressRepositoryCustom addressRepositoryCustom;
	
	@PutMapping("{id}/address")
	public void updateAddress(@PathVariable UUID id, @RequestBody Address addr) {
		orderService.addAddress(id, addr);
	}
	
	@PutMapping("/{id}/status")
	public void updateStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
		orderService.updateStatus(id, status);
	}
}

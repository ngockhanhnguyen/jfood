package com.kadajko.cart.domain.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kadajko.cart.domain.model.Cart;
import com.kadajko.cart.domain.model.CartItem;
import com.kadajko.cart.domain.repository.CartRepository;
import com.kadajko.cart.domain.service.CartService;
import com.kadajko.cart.exception.UnknownResourceException;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository repository;

    @Override
    public Cart createCart(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public Cart get(UUID id) {
        Cart cart = repository.findOne(id);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + id + " does not exist.");
        return cart;
    }

    @Override
    public Collection<CartItem> getItemsFromCart(UUID id) {
        Cart cart = repository.findOne(id);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + id + " does not exist.");
        return cart.getItems();
    }

    @Override
    public CartItem addItemToCart(UUID id, CartItem item) {
        Cart cart = repository.findOne(id);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + id + " does not exist.");
        for (CartItem i : cart.getItems()) {
			if (item.equals(i)) {
				i.setQuantity(item.getQuantity() + i.getQuantity());
				repository.save(cart);
				return i;
			}
		}
        
        cart.getItems().add(item);
        repository.save(cart);
        return item;
    }

    @Override
    public CartItem updateItemQuantity(UUID cartId, UUID productId, 
            Integer quantity) {
        Cart cart = repository.findOne(cartId);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + cartId + " does not exist.");
//        items = cart.getItems();
        List<CartItem> items = cart.getItems();
        int i;
        for (i = 0; i < items.size(); i++) {
			if (items.get(i).getProductId().equals(productId)) {
				items.get(i).setQuantity(quantity);
				cart.setItems(items);
				repository.save(cart);
				return items.get(i);
			}
		}

        throw new UnknownResourceException(
                "Product with id " + productId + " does not exist in cart.");
    }

    @Override
    public void deleteItem(UUID cartId, UUID productId) {
        Cart cart = repository.findOne(cartId);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + cartId + " does not exist.");
        
        List<CartItem> items = cart.getItems();
        int i;
        for (i = 0; i < items.size(); i++) {
			if (items.get(i).getProductId().equals(productId)) {
				items.remove(i);
				cart.setItems(items);
				repository.save(cart);
				return;
			}
		}
        
        throw new UnknownResourceException(
                "Product with id " + productId + " does not exist in cart.");
        
    }

    @Override
    public void emptyCart(UUID cartId) {
        Cart cart = repository.findOne(cartId);
        if (cart == null)
            throw new UnknownResourceException(
                    "Cart with id " + cartId + " does not exist.");
        cart.setItems(new ArrayList<CartItem>());
        repository.save(cart);
    }

    @Override
    public Collection<Cart> getAllCarts() {
        return repository.findAll();
    }
    
    

}

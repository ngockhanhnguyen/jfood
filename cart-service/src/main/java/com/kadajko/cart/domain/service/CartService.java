package com.kadajko.cart.domain.service;

import java.util.Collection;
import java.util.UUID;

import com.kadajko.cart.domain.model.Cart;
import com.kadajko.cart.domain.model.CartItem;

public interface CartService {
    Cart createCart(Cart cart);
    
    Cart get(UUID id);
    
    Collection<CartItem> getItemsFromCart(UUID id);
    
    CartItem addItemToCart(UUID id, CartItem item);
    
    CartItem updateItemQuantity(UUID cartId, UUID productId, Integer quantity);
    
    void deleteItem(UUID cartId, UUID productId);
    
    void emptyCart(UUID cartId);
}

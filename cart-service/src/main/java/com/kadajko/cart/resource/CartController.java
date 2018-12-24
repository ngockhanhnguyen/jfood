package com.kadajko.cart.resource;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kadajko.cart.domain.model.Cart;
import com.kadajko.cart.domain.model.CartItem;
import com.kadajko.cart.domain.service.CartService;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = new Cart();
        cart.setId(UUID.randomUUID());
        cart.setCreatedAt(new Date());
        
        cartService.createCart(cart);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/cart/")
                .path(cart.getId().toString())
                .build()
                .toUri());
        
        return new ResponseEntity<Cart>(
                cart, headers, HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public Collection<Cart> getAllCart() {
        return cartService.getAllCarts();
    }
    
    @PostMapping("/{id}")
    public CartItem addItemToCart(@PathVariable UUID id, 
            @RequestBody CartItem item) {
        return cartService.addItemToCart(id, item);
    }
    
    @GetMapping("/{id}")
    public Cart get(@PathVariable UUID id) {
        return cartService.get(id);
    }
    
    @GetMapping("/{id}/items")
    public Collection<CartItem> getItemsFromCart(@PathVariable UUID id) {
        return cartService.getItemsFromCart(id);
    }

    
    @PutMapping("/{cartId}/item/{productId}")
    public CartItem updateItemQuantity(@PathVariable UUID cartId, 
            @PathVariable UUID productId, @RequestParam Integer quantity) {
        return cartService.updateItemQuantity(cartId, productId, quantity);
    }
    
    @DeleteMapping("/{cartId}/item/{productId}")
    public void deleteItem(@PathVariable UUID cartId, 
            @PathVariable UUID productId) {
        cartService.deleteItem(cartId, productId);
    }
    
    @DeleteMapping("/{id}")
    public void emptyCart(@PathVariable UUID id) {
        cartService.emptyCart(id);
    }
    
    @GetMapping("/test")
    public String test(@Value("${spring.data.mongodb.host}") String mes) {
        return mes;
    }
}

package com.kadajko.cart.domain.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItem {
    private UUID productId;
    private Integer quantity;
    private Double price;
    
    @JsonIgnore
    private Double total;
    
    @JsonProperty
    public Double getTotal() {
        total = price * quantity;
        return total;
    }
    
    @JsonIgnore
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public UUID getProductId() {
        return productId;
    }
    
    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
}

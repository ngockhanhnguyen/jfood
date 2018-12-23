package com.kadajko.cart.domain.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "cart")
public class Cart {
    @Id
    private UUID id;
    
    @Field("created_at")
    @JsonIgnore
    private Date createdAt;
    
    @Field("items")
    private Map<UUID, CartItem> items = new HashMap<>();
    
    @Transient
    @JsonIgnore
    private Double total;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonIgnore
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Map<UUID, CartItem> getItems() {
        return items;
    }
    
    public void setItems(Map<UUID, CartItem> items) {
        this.items = items;
    }
    
    @JsonProperty
    public Double getTotal() {
        this.total = 0.0;
        if (items != null)
            for (CartItem cartItem : items.values()) {
                total += cartItem.getTotal();
            }
        
        return total;
    }
    
    @JsonIgnore
    public void setTotal(Double total) {
        this.total = total;
    }
}

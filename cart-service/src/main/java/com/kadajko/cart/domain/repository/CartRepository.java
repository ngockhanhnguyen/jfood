package com.kadajko.cart.domain.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kadajko.cart.domain.model.Cart;

public interface CartRepository extends MongoRepository<Cart, UUID>, 
    CartRepositoryCustom {

}

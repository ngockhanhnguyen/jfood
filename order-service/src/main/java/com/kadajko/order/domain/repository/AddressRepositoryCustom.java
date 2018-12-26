package com.kadajko.order.domain.repository;

import java.util.UUID;

import com.kadajko.order.domain.model.Address;

public interface AddressRepositoryCustom {
	Address findAddressByOrderId(UUID id);
}

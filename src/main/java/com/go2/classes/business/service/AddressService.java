package com.go2.classes.business.service;

import java.util.List;

import com.go2.classes.models.Address;

public interface AddressService {

	Address findById(Long id);

	List<Address> findAll();

	Address save(Address entity);

	Address update(Address entity);

	Address create(Address entity);

	void delete(Long id);

}

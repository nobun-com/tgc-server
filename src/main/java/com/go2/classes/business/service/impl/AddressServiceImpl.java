package com.go2.classes.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import com.go2.classes.models.Address;
import com.go2.classes.models.jpa.AddressEntity;
import com.go2.classes.business.service.AddressService;
import com.go2.classes.business.service.mapping.AddressServiceMapper;
import com.go2.classes.data.repository.jpa.AddressJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AddressServiceImpl implements AddressService {

	@Resource
	private AddressJpaRepository addressJpaRepository;

	@Resource
	private AddressServiceMapper addressServiceMapper;
	
	@Override
	public Address findById(Long id) {
		AddressEntity addressEntity = addressJpaRepository.findOne(id);
		return addressServiceMapper.mapAddressEntityToAddress(addressEntity);
	}

	@Override
	public List<Address> findAll() {
		Iterable<AddressEntity> entities = addressJpaRepository.findAll();
		List<Address> beans = new ArrayList<Address>();
		for(AddressEntity addressEntity : entities) {
			beans.add(addressServiceMapper.mapAddressEntityToAddress(addressEntity));
		}
		return beans;
	}

	@Override
	public Address save(Address address) {
		return update(address) ;
	}

	@Override
	public Address create(Address address) {
		AddressEntity addressEntity = null;
		if(address.getId() != null) {
			addressEntity = addressJpaRepository.findOne(address.getId());
		}
		if( addressEntity != null ) {
			throw new IllegalStateException("address.already.exists");
		}
		addressEntity = new AddressEntity();
		addressServiceMapper.mapAddressToAddressEntity(address, addressEntity);
		AddressEntity addressEntitySaved = addressJpaRepository.save(addressEntity);
		return addressServiceMapper.mapAddressEntityToAddress(addressEntitySaved);
	}

	@Override
	public Address update(Address address) {
		AddressEntity addressEntity = null;
		if(!Objects.isNull(address.getId())) {
			addressEntity = addressJpaRepository.findOne(address.getId());
		}
		if(Objects.isNull(addressEntity)) {
			return create(address);
		}
		addressServiceMapper.mapAddressToAddressEntity(address, addressEntity);
		AddressEntity addressEntitySaved = addressJpaRepository.save(addressEntity);
		return addressServiceMapper.mapAddressEntityToAddress(addressEntitySaved);
	}

	@Override
	public void delete(Long id) {
		addressJpaRepository.delete(id);
	}

	public AddressJpaRepository getAddressJpaRepository() {
		return addressJpaRepository;
	}

	public void setAddressJpaRepository(AddressJpaRepository addressJpaRepository) {
		this.addressJpaRepository = addressJpaRepository;
	}

	public AddressServiceMapper getAddressServiceMapper() {
		return addressServiceMapper;
	}

	public void setAddressServiceMapper(AddressServiceMapper addressServiceMapper) {
		this.addressServiceMapper = addressServiceMapper;
	}

}

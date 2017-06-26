package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Address;
import com.go2.classes.models.jpa.AddressEntity;

@Component
public class AddressServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public AddressServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Address mapAddressEntityToAddress(AddressEntity addressEntity) {
		if(addressEntity == null) {
			return null;
		}

		Address address = map(addressEntity, Address.class);
		return address;
	}
	
	public void mapAddressToAddressEntity(Address address, AddressEntity addressEntity) {
		if(address == null) {
			return;
		}
		map(address, addressEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}
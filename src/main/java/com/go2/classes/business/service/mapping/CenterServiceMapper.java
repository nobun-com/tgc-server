package com.go2.classes.business.service.mapping;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.go2.classes.business.service.AddressService;
import com.go2.classes.models.Center;
import com.go2.classes.models.jpa.CenterEntity;
import com.go2.classes.rest.common.Utilities;
import com.go2.classes.models.jpa.AdminEntity;
import com.go2.classes.models.jpa.AddressEntity;

@Component
public class CenterServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	@Resource
	private AddressService addressService;
	
	@Resource
	private AddressServiceMapper addressServiceMapper;
	
	public CenterServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Center mapCenterEntityToCenter(CenterEntity centerEntity) {
		if(centerEntity == null) {
			return null;
		}

		Center center = map(centerEntity, Center.class);
		if(centerEntity.getAdmin() != null) {
			center.setAdminId(centerEntity.getAdmin().getId());
		}
		if(centerEntity.getAddress() != null) {
			center.setAddress(addressServiceMapper.mapAddressEntityToAddress(centerEntity.getAddress()));
		}
		return center;
	}
	
	public void mapCenterToCenterEntity(Center center, CenterEntity centerEntity) {
		if(center == null) {
			return;
		}

		map(center, centerEntity);

		if( hasLinkToAdmin(center) ) {
			AdminEntity admin1 = new AdminEntity();
			admin1.setId( center.getAdminId() );
			centerEntity.setAdmin( admin1 );
		} else {
			centerEntity.setAdmin( null );
		}

		if( hasLinkToAddress(center) ) {
			AddressEntity addressEntity = new AddressEntity();
			addressServiceMapper.mapAddressToAddressEntity(center.getAddress(), addressEntity);
			centerEntity.setAddress(addressEntity);
		} else {
			centerEntity.setAddress( null );
		}

	}
	
	private boolean hasLinkToAdmin(Center center) {
		if(center.getAdminId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToAddress(Center center) {
		if(center.getAddress() != null) {
			return true;
		}
		return false;
	}

	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Map<String, Object> mapResultToJSONMap(Object[] entitie) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerName", entitie[0]);
		map.put("logoName", entitie[1]);
		map.put("centerId", entitie[2]);
		map.put("classCount", entitie[3]);
		return map;
	}

}
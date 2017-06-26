package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.CenterAssetEntity;
import com.go2.classes.models.jpa.CenterAssetEntityEntity;
import com.go2.classes.models.jpa.CenterEntity;
import com.go2.classes.models.jpa.AssetEntity;

@Component
public class CenterAssetEntityServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public CenterAssetEntityServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public CenterAssetEntity mapCenterAssetEntityEntityToCenterAssetEntity(CenterAssetEntityEntity centerAssetEntityEntity) {
		if(centerAssetEntityEntity == null) {
			return null;
		}

		CenterAssetEntity centerAssetEntity = map(centerAssetEntityEntity, CenterAssetEntity.class);

		if(centerAssetEntityEntity.getCenter() != null) {
			centerAssetEntity.setCenterId(centerAssetEntityEntity.getCenter().getId());
		}
		if(centerAssetEntityEntity.getAsset() != null) {
			centerAssetEntity.setAssetEntityId(centerAssetEntityEntity.getAsset().getId());
		}
		return centerAssetEntity;
	}
	
	public void mapCenterAssetEntityToCenterAssetEntityEntity(CenterAssetEntity centerAssetEntity, CenterAssetEntityEntity centerAssetEntityEntity) {
		if(centerAssetEntity == null) {
			return;
		}
		map(centerAssetEntity, centerAssetEntityEntity);
		if( hasLinkToCenter(centerAssetEntity) ) {
			CenterEntity center1 = new CenterEntity();
			center1.setId( centerAssetEntity.getCenterId() );
			centerAssetEntityEntity.setCenter( center1 );
		} else {
			centerAssetEntityEntity.setCenter( null );
		}

		//--- Link mapping ( link : centerAssetEntity )
		if( hasLinkToAsset(centerAssetEntity) ) {
			AssetEntity asset2 = new AssetEntity();
			asset2.setId( centerAssetEntity.getAssetEntityId() );
			centerAssetEntityEntity.setAsset( asset2 );
		} else {
			centerAssetEntityEntity.setAsset( null );
		}

	}
	
	private boolean hasLinkToCenter(CenterAssetEntity centerAssetEntity) {
		if(centerAssetEntity.getCenterId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToAsset(CenterAssetEntity centerAssetEntity) {
		if(centerAssetEntity.getAssetEntityId() != null) {
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

}
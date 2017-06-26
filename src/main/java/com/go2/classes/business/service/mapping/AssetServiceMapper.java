package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.Asset;
import com.go2.classes.models.jpa.AssetEntity;

@Component
public class AssetServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public AssetServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public Asset mapAssetEntityToAsset(AssetEntity assetEntity) {
		if(assetEntity == null) {
			return null;
		}
		Asset asset = map(assetEntity, Asset.class);
		return asset;
	}
	
	public void mapAssetToAssetEntity(Asset asset, AssetEntity assetEntity) {
		if(asset == null) {
			return;
		}
		map(asset, assetEntity);
	}
	
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}
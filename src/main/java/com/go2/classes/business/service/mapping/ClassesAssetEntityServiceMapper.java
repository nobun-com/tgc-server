package com.go2.classes.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.go2.classes.models.ClassesAssetEntity;
import com.go2.classes.models.jpa.ClassesAssetEntityEntity;
import com.go2.classes.models.jpa.AssetEntity;
import com.go2.classes.models.jpa.ClassesEntity;

@Component
public class ClassesAssetEntityServiceMapper extends AbstractServiceMapper {

	private ModelMapper modelMapper;
	
	public ClassesAssetEntityServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public ClassesAssetEntity mapClassesAssetEntityEntityToClassesAssetEntity(ClassesAssetEntityEntity classesAssetEntityEntity) {
		if(classesAssetEntityEntity == null) {
			return null;
		}

		ClassesAssetEntity classesAssetEntity = map(classesAssetEntityEntity, ClassesAssetEntity.class);

		if(classesAssetEntityEntity.getAsset() != null) {
			classesAssetEntity.setAssetEntityId(classesAssetEntityEntity.getAsset().getId());
		}
		if(classesAssetEntityEntity.getClasses() != null) {
			classesAssetEntity.setClassesId(classesAssetEntityEntity.getClasses().getId());
		}
		return classesAssetEntity;
	}
	
	public void mapClassesAssetEntityToClassesAssetEntityEntity(ClassesAssetEntity classesAssetEntity, ClassesAssetEntityEntity classesAssetEntityEntity) {
		if(classesAssetEntity == null) {
			return;
		}

		map(classesAssetEntity, classesAssetEntityEntity);

		if( hasLinkToAsset(classesAssetEntity) ) {
			AssetEntity asset1 = new AssetEntity();
			asset1.setId( classesAssetEntity.getAssetEntityId() );
			classesAssetEntityEntity.setAsset( asset1 );
		} else {
			classesAssetEntityEntity.setAsset( null );
		}

		if( hasLinkToClasses(classesAssetEntity) ) {
			ClassesEntity classes2 = new ClassesEntity();
			classes2.setId( classesAssetEntity.getClassesId() );
			classesAssetEntityEntity.setClasses( classes2 );
		} else {
			classesAssetEntityEntity.setClasses( null );
		}

	}
	
	private boolean hasLinkToAsset(ClassesAssetEntity classesAssetEntity) {
		if(classesAssetEntity.getAssetEntityId() != null) {
			return true;
		}
		return false;
	}

	private boolean hasLinkToClasses(ClassesAssetEntity classesAssetEntity) {
		if(classesAssetEntity.getClassesId() != null) {
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
package com.go2.classes.web.listitem;

import com.go2.classes.models.ClassesAssetEntity;
import com.go2.classes.web.common.ListItem;

public class ClassesAssetEntityListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ClassesAssetEntityListItem(ClassesAssetEntity classesAssetEntity) {
		super();

		this.value = ""
			 + classesAssetEntity.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = classesAssetEntity.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}

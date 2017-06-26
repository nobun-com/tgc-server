package com.go2.classes.web.listitem;

import com.go2.classes.models.CenterAssetEntity;
import com.go2.classes.web.common.ListItem;

public class CenterAssetEntityListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public CenterAssetEntityListItem(CenterAssetEntity centerAssetEntity) {
		super();

		this.value = ""
			 + centerAssetEntity.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = centerAssetEntity.toString();
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

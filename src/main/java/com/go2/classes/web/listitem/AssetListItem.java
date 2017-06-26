package com.go2.classes.web.listitem;

import com.go2.classes.models.Asset;
import com.go2.classes.web.common.ListItem;

public class AssetListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AssetListItem(Asset asset) {
		super();

		this.value = ""
			 + asset.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = asset.toString();
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

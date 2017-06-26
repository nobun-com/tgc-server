package com.go2.classes.web.listitem;

import com.go2.classes.models.Center;
import com.go2.classes.web.common.ListItem;

public class CenterListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public CenterListItem(Center center) {
		super();

		this.value = ""
			 + center.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = center.toString();
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

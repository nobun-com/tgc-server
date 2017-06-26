package com.go2.classes.web.listitem;

import com.go2.classes.models.Admin;
import com.go2.classes.web.common.ListItem;

public class AdminListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AdminListItem(Admin admin) {
		super();

		this.value = ""
			 + admin.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = admin.toString();
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

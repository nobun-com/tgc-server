package com.go2.classes.web.listitem;

import com.go2.classes.models.SuperAdmin;
import com.go2.classes.web.common.ListItem;

public class SuperAdminListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public SuperAdminListItem(SuperAdmin superAdmin) {
		super();

		this.value = ""
			 + superAdmin.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = superAdmin.toString();
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

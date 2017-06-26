package com.go2.classes.web.listitem;

import com.go2.classes.models.Classes;
import com.go2.classes.web.common.ListItem;

public class ClassesListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ClassesListItem(Classes classes) {
		super();

		this.value = ""
			 + classes.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = classes.toString();
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

package com.go2.classes.web.listitem;

import com.go2.classes.models.StudentClasses;
import com.go2.classes.web.common.ListItem;

public class StudentClassesListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public StudentClassesListItem(StudentClasses studentClasses) {
		super();

		this.value = ""
			 + studentClasses.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = studentClasses.toString();
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

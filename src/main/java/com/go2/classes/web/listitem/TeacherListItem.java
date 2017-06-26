package com.go2.classes.web.listitem;

import com.go2.classes.models.Teacher;
import com.go2.classes.web.common.ListItem;

public class TeacherListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public TeacherListItem(Teacher teacher) {
		super();

		this.value = ""
			 + teacher.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = teacher.toString();
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

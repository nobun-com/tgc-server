package com.go2.classes.web.listitem;

import com.go2.classes.models.ClassesTeacher;
import com.go2.classes.web.common.ListItem;

public class ClassesTeacherListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ClassesTeacherListItem(ClassesTeacher classesTeacher) {
		super();

		this.value = ""
			 + classesTeacher.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = classesTeacher.toString();
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

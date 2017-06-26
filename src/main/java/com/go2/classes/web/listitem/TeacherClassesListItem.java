package com.go2.classes.web.listitem;

import com.go2.classes.models.TeacherClasses;
import com.go2.classes.web.common.ListItem;

public class TeacherClassesListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public TeacherClassesListItem(TeacherClasses teacherClasses) {
		super();

		this.value = ""
			 + teacherClasses.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = teacherClasses.toString();
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

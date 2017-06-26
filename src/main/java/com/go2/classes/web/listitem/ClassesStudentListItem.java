package com.go2.classes.web.listitem;

import com.go2.classes.models.ClassesStudent;
import com.go2.classes.web.common.ListItem;

public class ClassesStudentListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ClassesStudentListItem(ClassesStudent classesStudent) {
		super();

		this.value = ""
			 + classesStudent.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = classesStudent.toString();
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

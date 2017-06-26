package com.go2.classes.web.listitem;

import com.go2.classes.models.Student;
import com.go2.classes.web.common.ListItem;

public class StudentListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public StudentListItem(Student student) {
		super();

		this.value = ""
			 + student.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = student.toString();
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

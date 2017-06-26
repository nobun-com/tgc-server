package com.go2.classes.web.listitem;

import com.go2.classes.models.ClassesTimetable;
import com.go2.classes.web.common.ListItem;

public class ClassesTimetableListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public ClassesTimetableListItem(ClassesTimetable classesTimetable) {
		super();

		this.value = ""
			 + classesTimetable.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = classesTimetable.toString();
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

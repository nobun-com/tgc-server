package com.go2.classes.web.listitem;

import com.go2.classes.models.TimeTable;
import com.go2.classes.web.common.ListItem;

public class TimeTableListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public TimeTableListItem(TimeTable timeTable) {
		super();

		this.value = ""
			 + timeTable.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = timeTable.toString();
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

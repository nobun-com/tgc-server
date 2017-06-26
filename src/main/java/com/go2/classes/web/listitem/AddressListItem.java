package com.go2.classes.web.listitem;

import com.go2.classes.models.Address;
import com.go2.classes.web.common.ListItem;

public class AddressListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AddressListItem(Address address) {
		super();

		this.value = ""
			 + address.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = address.toString();
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

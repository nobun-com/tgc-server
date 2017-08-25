package com.go2.classes.common;

import java.text.SimpleDateFormat;

public class Utilities {

	public final static SimpleDateFormat dateWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
	public final static SimpleDateFormat datePicker = new SimpleDateFormat("MM/dd/yyyy");
	public final static SimpleDateFormat dayOnly = new SimpleDateFormat("EEE");
	public final static SimpleDateFormat timeOnly = new SimpleDateFormat("hh:mm a");

}

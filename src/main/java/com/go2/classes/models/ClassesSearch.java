package com.go2.classes.models;

import java.io.Serializable;
import java.util.Objects;

import com.go2.classes.rest.common.Utilities;

public class ClassesSearch implements Serializable {

    private static final long serialVersionUID = 1L;

	String startDate;
	String age;
	String area;
	String center;
	String[] fees;
	String[] day;
	String[] time;
	String[] interest;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String[] getFees() {
		return fees;
	}
	public void setFees(String[] fees) {
		this.fees = fees;
	}
	public String[] getDay() {
		return day;
	}
	public void setDay(String[] day) {
		this.day = day;
	}
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	public String[] getInterest() {
		return interest;
	}
	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	@Override
	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(startDate);
        sb.append("|");
        sb.append(age);
        sb.append("|");
        sb.append(area);
        sb.append("|");
        sb.append(center);
        sb.append("|[");
        sb.append(fees);
        sb.append("]|");
        sb.append(center);
        sb.append("|");
        return sb.toString(); 
    }
	
	public String getSearchQuery(){
		String sql = "select TT.* from time_table TT, classes CC where TT.classes_id = CC.id";
		sql = sql + getAgePredicate();
		sql = sql + getStartDatePredicate();
		sql = sql + getCenterPredicate();
		
		return sql;
	}
	
	private String getAgePredicate() {
		if(isEmpty(age)) { return ""; }
		String sql = " and CC.max_age >= " + age + " and CC.min_age <= " + age;
		return sql;
	}
	
	private String getStartDatePredicate() {
		if(isEmpty(startDate)) { return ""; }
		String sql = " and CC.start_date >= '" + Utilities.dateWithoutTime.format(startDate) + "'";
		return sql;
	}
	
	private String getCenterPredicate() {
		if(isEmpty(center)) { return ""; }
		String sql = " and CC.class_name like '%" + center + "%'";
		return sql;
	}
	
	private boolean isEmpty(String val) {
		if(Objects.isNull(val))
			return true;
		if(val.trim().equals(""))
			return true;
		return false;
	}
	
}

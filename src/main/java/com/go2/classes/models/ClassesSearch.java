package com.go2.classes.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.go2.classes.common.Utilities;

public class ClassesSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	Date startDate;
	String age;
	String area;
	String center;
	String minFees;
	String maxFees;
	String[] day;
	String[] time;
	String[] interest;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
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

	public String getMinFees() {
		return minFees;
	}

	public void setMinFees(String minFees) {
		this.minFees = minFees;
	}

	public String getMaxFees() {
		return maxFees;
	}

	public void setMaxFees(String maxFees) {
		this.maxFees = maxFees;
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
		sb.append(minFees);
		sb.append("]|");
		sb.append(center);
		sb.append("|");
		return sb.toString();
	}

	public String getSearchByCenterQuery() {
		String sql = "select CN.center_name as 'centerName', CN.logo_name as 'logo', CN.id as 'id', count(*) as 'count' from time_table TT, classes CC, center CN, address AD where TT.classes_id = CC.id and CC.center_id = CN.id and CN.address_id = AD.id";
		sql = sql + getPredicate();
		sql = sql + " GROUP BY CN.id";
		return sql;
	}

	public String getSearchClassesByCenretQuery(Long centerId) {
		String sql = "select TT.* from time_table TT, classes CC, center CN, address AD where TT.classes_id = CC.id and CC.center_id = CN.id and CN.address_id = AD.id";
		sql = sql + getPredicate();
		sql = sql + " and CN.id = " + centerId;
		return sql;
	}

	private String getPredicate() {
		String sql = getAgePredicate();
		sql = sql + getStartDatePredicate();
		sql = sql + getCenterPredicate();
		sql = sql + getFeesPredicate();
		sql = sql + getAreaPredicate();
		sql = sql + getInterestPredicate();
		sql = sql + getDayPredicate();
		sql = sql + getTimePredicate();
		return sql;
	}

	private String getAgePredicate() {
		if (isEmpty(age)) {
			return "";
		}
		String sql = " and CC.max_age >= " + age + " and CC.min_age <= " + age;
		return sql;
	}

	private String getFeesPredicate() {
		String sql = "";
		if (!isEmpty(minFees)) {
			sql = " and CC.fee >= " + minFees.substring(1);
		}
		if (!isEmpty(maxFees)) {
			sql += " and CC.fee <= " + maxFees.substring(1);
		}
		return sql;
	}

	private String getStartDatePredicate() {
		if (startDate == null) {
			return "";
		}
		String sql = " and CC.start_date >= '" + Utilities.dateWithoutTime.format(startDate) + "'";
		return sql;
	}

	private String getCenterPredicate() {
		if (isEmpty(center)) {
			return "";
		}
		String sql = " and CC.class_name like '%" + center + "%'";
		return sql;
	}

	private String getAreaPredicate() {
		if (isEmpty(area)) {
			return "";
		}
		if (area.equals("ALL")) {
			return "";
		}
		String sql = " and AD.area = '" + area + "'";
		return sql;
	}

	private String getInterestPredicate() {
		if (Objects.isNull(interest) || interest.length == 0) {
			return "";
		}
		String sql = " and CC.category_id IN (" + String.join(", ", interest) + ")";
		return sql;
	}

	private String getTimePredicate() {
		if (Objects.isNull(time) || time.length == 0) {
			return "";
		}
		String sql = " and (";
		for (int index = 0; index < time.length; index++) {
			String tokens[] = time[index].split("-");
			sql += "(((hour(TT.start_time)*60) + minute(TT.start_time)) >= " + tokens[0];
			sql += " and ((hour(TT.start_time)*60) + minute(TT.start_time)) <= " + tokens[1] + ")";

			if (index < time.length - 1) {
				sql += " or ";
			}
		}
		sql += ")";
		return sql;
	}

	private String getDayPredicate() {
		if (Objects.isNull(day) || day.length == 0) {
			return "";
		}
		String sql = " and DAYNAME(TT.start_time) IN ('" + String.join("', '", day) + "')";
		return sql;
	}

	private boolean isEmpty(String val) {
		if (Objects.isNull(val))
			return true;
		if (val.trim().equals(""))
			return true;
		return false;
	}

}

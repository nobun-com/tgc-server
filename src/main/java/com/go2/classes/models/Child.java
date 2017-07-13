package com.go2.classes.models;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Child implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String gender;

	@NotNull
	private Date dateOfBirth;

	@NotNull
	private String location;

	@NotNull
	private Long studentId;

	@Size(max = 100)
	private String imageName;

	@Size(max = 500)
	private String imageUrl;

	Integer[] interest = new Integer[0];

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getInterest() {
		return interest;
	}

	public void setInterest(Integer[] interest) {
		this.interest = interest;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("|[");
		sb.append(name);
		sb.append("]|");
		sb.append(dateOfBirth);
		sb.append("|");
		sb.append(location);
		sb.append("|");
		sb.append(studentId);
		sb.append("|");
		sb.append(imageName);
		sb.append("|");
		sb.append(imageUrl);
		return sb.toString();
	}
}

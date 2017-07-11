package com.go2.classes.models;

import java.io.Serializable;

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
    private String dateOfBirth;

    @NotNull
    private String location;
    
    @NotNull
    private Student student;
    
    @Size( max = 100 )
    private String imageName;

    @Size( max = 500 )
    private String imageUrl;

    
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
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
        sb.append(student);
        sb.append("|");
        sb.append(imageName);
        sb.append("|");
        sb.append(imageUrl);
        return sb.toString(); 
    } 
}

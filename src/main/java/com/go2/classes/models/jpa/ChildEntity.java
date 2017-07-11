package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="child")
public class ChildEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="gender", length=6)
    private String gender;
    
    @Column(name="date_of_birth", nullable=false)
    private String dateOfBirth;

    @Column(name="location", nullable=false, length=50)
    private String location;
    
    @ManyToOne
    @JoinColumn(name="student_id", referencedColumnName="id")
    private StudentEntity student;
    
    @Column(name="image_name", length=100)
    private String imageName;

    @Column(name="image_url", length=500)
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


	public StudentEntity getStudent() {
		return student;
	}


	public void setStudent(StudentEntity student) {
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
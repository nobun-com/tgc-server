package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "child")
public class ChildEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "gender", length = 6)
	private String gender;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "location", nullable = false, length = 50)
	private String location;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;

	@Column(name = "image_url", length = 500)
	private String imageUrl;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "child")
	// @OneToMany(mappedBy = "child", targetEntity = ChildInterestsEntity.class)
	private List<ChildInterestsEntity> listOfChildInterests;

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ChildInterestsEntity> getListOfChildInterests() {
		return listOfChildInterests;
	}

	public void setListOfChildInterests(List<ChildInterestsEntity> listOfChildInterests) {
		this.listOfChildInterests = listOfChildInterests;
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
		sb.append(user);
		sb.append("|");
		sb.append(imageUrl);
		return sb.toString();
	}
}

package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Educator implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    private Date createdTime;

    @Size( max = 60 )
    private String email;

    @NotNull
    @Size( min = 1, max = 20 )
    private String firstName;

    @Size( max = 6 )
    private String gender;

    @NotNull
    @Size( min = 1, max = 20 )
    private String lastName;

    @NotNull
    @Size( min = 1, max = 20 )
    private String password;

    @Size( max = 12 )
    private String phone;

    @Size( max = 500 )
    private String profileImageUrl;

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }
    public String getGender() {
        return this.gender;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
 
	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(firstName);
        sb.append("|");
        sb.append(gender);
        sb.append("|");
        sb.append(lastName);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(phone);
        sb.append("|");
        sb.append(profileImageUrl);
        return sb.toString(); 
    } 
}

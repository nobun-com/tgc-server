package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    private Date createdTime;

    @Size( max = 60 )
    private String email;

    @NotNull
    @Size( min = 1, max = 20 )
    private String name;

    @Size( max = 6 )
    private String gender;

    @NotNull
    @Size( min = 1, max = 20 )
    private String password;

    @Size( max = 12 )
    private String phone;

    @Size( max = 12 )
    private String refrralCode;

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

    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }
    public String getGender() {
        return this.gender;
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

    public String getRefrralCode() {
		return refrralCode;
	}
	public void setRefrralCode(String refrralCode) {
		this.refrralCode = refrralCode;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(gender);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(phone);
        return sb.toString(); 
    } 
}

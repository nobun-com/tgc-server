package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    private Date createdTime;

    @Size( max = 60 )
    private String email;

    @NotNull
    @Size( min = 1, max = 20 )
    private String password;

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

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(password);
        return sb.toString(); 
    } 
}

package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    private Date createdTime;

    @NotNull
    @Size( min = 1, max = 200 )
    private String path;

    @NotNull
    @Size( min = 1, max = 10 )
    private String type;

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

    public void setPath( String path ) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }

    public void setType( String type ) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(path);
        sb.append("|");
        sb.append(type);
        return sb.toString(); 
    } 
}

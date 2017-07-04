package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class TimeTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private Classes classes;

    @NotNull
    private Date endTime;

    @NotNull
    private Date startTime;

    @Size( max = 255 )
    private String status;

    public TimeTable() {
    }
    public TimeTable(Classes classes, Date start, Date end) {
    	this.classes = classes;
    	this.startTime = start;
    	this.endTime = end;
	}

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

    public void setEndTime( Date endTime ) {
        this.endTime = endTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }

    public void setStartTime( Date startTime ) {
        this.startTime = startTime;
    }
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|[");
        sb.append(classes);
        sb.append("]|");
        sb.append(endTime);
        sb.append("|");
        sb.append(startTime);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 
}

package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class UserCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    private Date date;
    
    @NotNull
    private Long userId;

    @NotNull
    private Long timeTableId;

    @NotNull
    private String status;

    public UserCart() {
		super();
	}

	public UserCart(Long userId, Long timeTableId) {
		super();
		this.userId = userId;
		this.timeTableId = timeTableId;
		this.status = "InCart";
		this.date = new Date();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(Long timeTableId) {
		this.timeTableId = timeTableId;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(date);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(timeTableId);
        return sb.toString(); 
    } 
}

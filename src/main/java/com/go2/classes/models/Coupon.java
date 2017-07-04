package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String code;

    @NotNull
    @Size( min = 1, max = 10 )
    private String value;

    @Size( max = 10)
    private String status;

    private Date startDate;

    private Date expiryDate;
    
    public Coupon() { }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(code);
        sb.append("|");
        sb.append(value);
        sb.append("|");
        sb.append(startDate);
        sb.append("|");
        sb.append(expiryDate);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 
}

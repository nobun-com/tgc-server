package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="coupens")
public class CoupensEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="code", nullable=false, length=255)
    private String code;

    @Column(name="value", nullable=false, length=100)
    private String value;

    @Column(name="start_date", nullable=false, length=100)
    private Date startDate;

    @Column(name="expirey_date", nullable=false, length=100)
    private Date expireyDate;

    @Column(name="status", nullable=false, length=100)
    private String status;

    public CoupensEntity(){}
    
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpireyDate() {
		return expireyDate;
	}

	public void setExpireyDate(Date expireyDate) {
		this.expireyDate = expireyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(code);
        sb.append("|");
        sb.append(value);
        sb.append("|");
        sb.append(startDate);
        sb.append("|");
        sb.append(expireyDate);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 
}

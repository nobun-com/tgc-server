package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="coupon")
public class CouponEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="code", nullable=false, length=100)
    private String code;

    @Column(name="value", nullable=false, length=10)
    private String value;

    @Column(name="start_date", nullable=false)
    private Date startDate;

    @Column(name="expiry_date", nullable=false)
    private Date expiryDate;

    @Column(name="status", nullable=false, length=10)
    private String status;

    public CouponEntity(){}
    
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
        sb.append(expiryDate);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 
}

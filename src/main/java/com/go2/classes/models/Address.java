package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    @Size( min = 1, max = 100 )
    private String addressLine1;

    @Size( max = 50 )
    private String addressLine2;

	@Size( max = 50 )
    private String area;

    @Size( max = 50 )
    private String district;

    private Date createdTime;

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public void setAddressLine1( String addressLine1 ) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine2( String addressLine2 ) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(addressLine1);
        sb.append("|");
        sb.append(addressLine2);
        sb.append("|");
        sb.append(area);
        sb.append("|");
        sb.append(district);
        sb.append("|");
        sb.append(createdTime);
        return sb.toString(); 
    } 
}

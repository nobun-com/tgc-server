package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    //@NotNull
    private Long id;

    @NotNull
    @Size( min = 1, max = 500 )
    private String about;

    @NotNull
    @Size( min = 1, max = 50 )
    private String centerName;

    private Date createdTime;

    @Size( max = 100 )
    private String logoName;

    @Size( max = 500 )
    private String logoUrl;

    @Size( max = 500 )
    private String amenities;
    
    private Address address;

    private Long adminId;

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public void setAbout( String about ) {
        this.about = about;
    }
    public String getAbout() {
        return this.about;
    }

    public void setCenterName( String centerName ) {
        this.centerName = centerName;
    }
    public String getCenterName() {
        return this.centerName;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setLogoName( String logoName ) {
        this.logoName = logoName;
    }
    public String getLogoName() {
        return this.logoName;
    }

    public void setLogoUrl( String logoUrl ) {
        this.logoUrl = logoUrl;
    }
    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }
    public Address getAddress() {
        return this.address;
    }

    public void setAdminId( Long adminId ) {
        this.adminId = adminId;
    }
    public Long getAdminId() {
        return this.adminId;
    }
    public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(about);
        sb.append("|");
        sb.append(centerName);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(logoName);
        sb.append("|");
        sb.append(logoUrl);
        sb.append("|[");
        sb.append(address);
        sb.append("]|");
        sb.append(adminId);
        return sb.toString(); 
    } 
}

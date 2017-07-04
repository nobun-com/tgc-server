package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Area implements Serializable{

	private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;
    
    @Size( max = 100 )
    private String area;

    @NotNull
    @Size( min = 1, max = 100 )
    private String district;
    
    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
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
        sb.append(area);
        sb.append("|");
        sb.append(district);
        return sb.toString();
	}
}
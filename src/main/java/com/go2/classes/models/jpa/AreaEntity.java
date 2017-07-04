package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="area")

public class AreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="area", length=50)
    private String area;

    @Column(name="district", length=50)
    private String district;

    public AreaEntity() {
		super();
    }
    
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
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(area);
        sb.append("|");
        sb.append(district);
        return sb.toString(); 
    } 
}

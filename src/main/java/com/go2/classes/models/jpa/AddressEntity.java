package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @Column(name="address_line1", nullable=false, length=100)
    private String     addressLine1 ;

    @Column(name="address_line2", length=50)
    private String     addressLine2 ;

    @Column(name="area", length=50)
    private String area;

    @Column(name="district", length=50)
    private String     district      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @OneToMany(mappedBy="address", targetEntity=CenterEntity.class)
    private List<CenterEntity> listOfCenter;

    public AddressEntity() {
		super();
    }
    
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

    public void setListOfCenter( List<CenterEntity> listOfCenter ) {
        this.listOfCenter = listOfCenter;
    }
    public List<CenterEntity> getListOfCenter() {
        return this.listOfCenter;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
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

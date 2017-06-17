package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="center")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CenterEntity.countAll", query="SELECT COUNT(x) FROM CenterEntity x" )
} )
public class CenterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @Column(name="about", nullable=false, length=500)
    private String     about        ;

    @Column(name="center_name", nullable=false, length=50)
    private String     centerName   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @Column(name="logo_name", length=100)
    private String     logoName     ;

    @Column(name="logo_url", length=500)
    private String     logoUrl      ;

    @OneToMany(mappedBy="center", targetEntity=CenterAssetEntityEntity.class)
    private List<CenterAssetEntityEntity> listOfCenterAssetEntity;

    @ManyToOne
    @JoinColumn(name="admin_id", referencedColumnName="id")
    private AdminEntity admin       ;

    @ManyToOne
    @JoinColumn(name="address_id", referencedColumnName="id")
    private AddressEntity address     ;

    public CenterEntity() {
		super();
    }
    
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

    //--- DATABASE MAPPING : created_time ( DATETIME ) 
    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    //--- DATABASE MAPPING : logo_name ( VARCHAR ) 
    public void setLogoName( String logoName ) {
        this.logoName = logoName;
    }
    public String getLogoName() {
        return this.logoName;
    }

    //--- DATABASE MAPPING : logo_url ( VARCHAR ) 
    public void setLogoUrl( String logoUrl ) {
        this.logoUrl = logoUrl;
    }
    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setListOfCenterAssetEntity( List<CenterAssetEntityEntity> listOfCenterAssetEntity ) {
        this.listOfCenterAssetEntity = listOfCenterAssetEntity;
    }
    public List<CenterAssetEntityEntity> getListOfCenterAssetEntity() {
        return this.listOfCenterAssetEntity;
    }

    public void setAdmin( AdminEntity admin ) {
        this.admin = admin;
    }
    public AdminEntity getAdmin() {
        return this.admin;
    }

    public void setAddress( AddressEntity address ) {
        this.address = address;
    }
    public AddressEntity getAddress() {
        return this.address;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(about);
        sb.append("|");
        sb.append(centerName);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(logoName);
        sb.append("|");
        sb.append(logoUrl);
        return sb.toString(); 
    } 
}

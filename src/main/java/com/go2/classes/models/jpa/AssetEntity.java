package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="asset")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="AssetEntity.countAll", query="SELECT COUNT(x) FROM AssetEntity x" )
} )
public class AssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @Column(name="path", nullable=false, length=200)
    private String     path         ;

    @Column(name="type", nullable=false, length=10)
    private String     type         ;

    @OneToMany(mappedBy="asset", targetEntity=CenterAssetEntityEntity.class)
    private List<CenterAssetEntityEntity> listOfCenterAssetEntity;

    @OneToMany(mappedBy="asset", targetEntity=ClassesAssetEntityEntity.class)
    private List<ClassesAssetEntityEntity> listOfClassesAssetEntity;

    public AssetEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setPath( String path ) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }

    //--- DATABASE MAPPING : type ( VARCHAR ) 
    public void setType( String type ) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }

    public void setListOfCenterAssetEntity( List<CenterAssetEntityEntity> listOfCenterAssetEntity ) {
        this.listOfCenterAssetEntity = listOfCenterAssetEntity;
    }
    public List<CenterAssetEntityEntity> getListOfCenterAssetEntity() {
        return this.listOfCenterAssetEntity;
    }

    public void setListOfClassesAssetEntity( List<ClassesAssetEntityEntity> listOfClassesAssetEntity ) {
        this.listOfClassesAssetEntity = listOfClassesAssetEntity;
    }
    public List<ClassesAssetEntityEntity> getListOfClassesAssetEntity() {
        return this.listOfClassesAssetEntity;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(createdTime);
        sb.append("|");
        sb.append(path);
        sb.append("|");
        sb.append(type);
        return sb.toString(); 
    } 
}

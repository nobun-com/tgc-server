package com.go2.classes.models.jpa;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="classes_asset_entity")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClassesAssetEntityEntity.countAll", query="SELECT COUNT(x) FROM ClassesAssetEntityEntity x" )
} )
public class ClassesAssetEntityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Long       id           ;

    @ManyToOne
    @JoinColumn(name="asset_entity_id", referencedColumnName="id")
    private AssetEntity asset       ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes     ;

    public ClassesAssetEntityEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public void setAsset( AssetEntity asset ) {
        this.asset = asset;
    }
    public AssetEntity getAsset() {
        return this.asset;
    }

    public void setClasses( ClassesEntity classes ) {
        this.classes = classes;
    }
    public ClassesEntity getClasses() {
        return this.classes;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 

}

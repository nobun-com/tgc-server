package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="center_asset_entity")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CenterAssetEntityEntity.countAll", query="SELECT COUNT(x) FROM CenterAssetEntityEntity x" )
} )
public class CenterAssetEntityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Long       id           ;

    @ManyToOne
    @JoinColumn(name="center_id", referencedColumnName="id")
    private CenterEntity center      ;

    @ManyToOne
    @JoinColumn(name="asset_entity_id", referencedColumnName="id")
    private AssetEntity asset       ;

    public CenterAssetEntityEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public void setCenter( CenterEntity center ) {
        this.center = center;
    }
    public CenterEntity getCenter() {
        return this.center;
    }

    public void setAsset( AssetEntity asset ) {
        this.asset = asset;
    }
    public AssetEntity getAsset() {
        return this.asset;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 
}

package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;


public class CenterAssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Long id;

    @NotNull
    private Long centerId;

    @NotNull
    private Long assetEntityId;

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public void setCenterId( Long centerId ) {
        this.centerId = centerId;
    }
    public Long getCenterId() {
        return this.centerId;
    }

    public void setAssetEntityId( Long assetEntityId ) {
        this.assetEntityId = assetEntityId;
    }
    public Long getAssetEntityId() {
        return this.assetEntityId;
    }


    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(centerId);
        sb.append("|");
        sb.append(assetEntityId);
        sb.append("|");
        sb.append(id);
        return sb.toString(); 
    } 


}

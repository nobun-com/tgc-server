package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;


public class ClassesAssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private Long classesId;

    @NotNull
    private Long assetEntityId;

    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }

    public void setClassesId( Long classesId ) {
        this.classesId = classesId;
    }
    public Long getClassesId() {
        return this.classesId;
    }

    public void setAssetEntityId( Long assetEntityId ) {
        this.assetEntityId = assetEntityId;
    }
    public Long getAssetEntityId() {
        return this.assetEntityId;
    }


    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(classesId);
        sb.append("|");
        sb.append(assetEntityId);
        sb.append("|");
        sb.append(id);
        return sb.toString(); 
    } 
}

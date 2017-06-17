package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="files")
public class FilesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="uuid", nullable=false, length=255)
    private String uuid;

    @Column(name="path", length=255)
    private String path;

    public FilesEntity(){}
    
    public FilesEntity(String uuid, String path) {
		super();
    	this.uuid = uuid;
    	this.path = path;
    }
    
    public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(uuid);
        sb.append("|");
        sb.append(path);
        return sb.toString(); 
    } 
}

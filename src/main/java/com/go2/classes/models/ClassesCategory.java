package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ClassesCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private String category;
    

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|[");
        sb.append(category);
        return sb.toString(); 
    } 
}

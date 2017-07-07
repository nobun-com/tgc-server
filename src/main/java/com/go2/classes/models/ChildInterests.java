package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ChildInterests implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private String interest;
    
    @NotNull
    private Child child;

    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getInterest() {
		return interest;
	}


	public void setInterest(String interest) {
		this.interest = interest;
	}


	public Child getChild() {
		return child;
	}


	public void setChild(Child child) {
		this.child = child;
	}


	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|[");
        sb.append(interest);
        sb.append("]|");
        sb.append(child);
        return sb.toString(); 
    } 
}

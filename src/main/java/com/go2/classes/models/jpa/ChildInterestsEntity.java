package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="childInterestsEntity")
public class ChildInterestsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="interest", nullable=false, length=50)
    private String interest;
    
    @ManyToOne
    @JoinColumn(name="child_id", referencedColumnName="id")
    private ChildEntity child;

    
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


	public ChildEntity getChild() {
		return child;
	}


	public void setChild(ChildEntity child) {
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

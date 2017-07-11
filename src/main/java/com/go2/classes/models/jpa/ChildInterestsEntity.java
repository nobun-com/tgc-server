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
@Table(name="child_interests")
public class ChildInterestsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="classesCategory_id", referencedColumnName="id")
    private ClassesCategoryEntity classesCategory;
    
    @ManyToOne
    @JoinColumn(name="child_id", referencedColumnName="id")
    private ChildEntity child;

    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ClassesCategoryEntity getClassesCategory() {
		return classesCategory;
	}


	public void setClassesCategory(ClassesCategoryEntity classesCategory) {
		this.classesCategory = classesCategory;
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
        sb.append(classesCategory);
        sb.append("]|");
        sb.append(child);
        return sb.toString(); 
    } 
}

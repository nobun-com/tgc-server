package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="classes_category")
public class ClassesCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="category", nullable=false, length=100)
    private String category;

	@OneToMany(cascade = {CascadeType.ALL}, fetch= FetchType.EAGER, mappedBy = "classesCategory")
	//@OneToMany(mappedBy = "child", targetEntity = ChildInterestsEntity.class)
	private List<ChildInterestsEntity> listOfChildInterests;

    public ClassesCategoryEntity(){}
    
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

	public List<ChildInterestsEntity> getListOfChildInterests() {
		return listOfChildInterests;
	}

	public void setListOfChildInterests(List<ChildInterestsEntity> listOfChildInterests) {
		this.listOfChildInterests = listOfChildInterests;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(category);
        return sb.toString(); 
    } 
}

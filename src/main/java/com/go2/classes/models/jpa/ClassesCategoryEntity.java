package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

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

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(category);
        return sb.toString(); 
    } 
}

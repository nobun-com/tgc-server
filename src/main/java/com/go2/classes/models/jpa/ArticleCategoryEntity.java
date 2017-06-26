package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="article_category")
public class ArticleCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="category", nullable=false, length=100)
    private String category;

    public ArticleCategoryEntity(){}
    
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

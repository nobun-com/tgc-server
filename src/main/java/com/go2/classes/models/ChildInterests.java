package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ChildInterests implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotNull
	private ClassesCategory classesCategory;

	@NotNull
	private Child child;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClassesCategory getClassesCategory() {
		return classesCategory;
	}

	public void setClassesCategory(ClassesCategory classesCategory) {
		this.classesCategory = classesCategory;
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
		sb.append(classesCategory);
		sb.append("]|");
		sb.append(child);
		return sb.toString();
	}
}

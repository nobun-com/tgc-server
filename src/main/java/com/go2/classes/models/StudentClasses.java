package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;


public class StudentClasses implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long classesId;

    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStudentId( Long studentId ) {
        this.studentId = studentId;
    }
    public Long getStudentId() {
        return this.studentId;
    }

    public void setClassesId( Long classesId ) {
        this.classesId = classesId;
    }
    public Long getClassesId() {
        return this.classesId;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(studentId);
        sb.append("|");
        sb.append(classesId);
        sb.append("|");
        sb.append(id);
        return sb.toString(); 
    } 
}

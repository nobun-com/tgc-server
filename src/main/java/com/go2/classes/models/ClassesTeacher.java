package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;


public class ClassesTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;

    @NotNull
    private Long classesId;

    @NotNull
    private Long teacherId;

    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }

    public void setClassesId( Long classesId ) {
        this.classesId = classesId;
    }
    public Long getClassesId() {
        return this.classesId;
    }

    public void setTeacherId( Long teacherId ) {
        this.teacherId = teacherId;
    }
    public Long getTeacherId() {
        return this.teacherId;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(classesId);
        sb.append("|");
        sb.append(teacherId);
        sb.append("|");
        sb.append(id);
        return sb.toString(); 
    } 
}

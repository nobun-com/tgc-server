package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="classes_teacher")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClassesTeacherEntity.countAll", query="SELECT COUNT(x) FROM ClassesTeacherEntity x" )
} )
public class ClassesTeacherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;

    @ManyToOne
    @JoinColumn(name="teacher_id", referencedColumnName="id")
    private TeacherEntity teacher     ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes     ;

    public ClassesTeacherEntity() {
		super();
    }
    
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setTeacher( TeacherEntity teacher ) {
        this.teacher = teacher;
    }
    public TeacherEntity getTeacher() {
        return this.teacher;
    }

    public void setClasses( ClassesEntity classes ) {
        this.classes = classes;
    }
    public ClassesEntity getClasses() {
        return this.classes;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 

}

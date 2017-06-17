package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="teacher_classes")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TeacherClassesEntity.countAll", query="SELECT COUNT(x) FROM TeacherClassesEntity x" )
} )
public class TeacherClassesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes     ;

    @ManyToOne
    @JoinColumn(name="teacher_id", referencedColumnName="id")
    private TeacherEntity teacher     ;

    public TeacherClassesEntity() {
		super();
    }
    
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setClasses( ClassesEntity classes ) {
        this.classes = classes;
    }
    public ClassesEntity getClasses() {
        return this.classes;
    }

    public void setTeacher( TeacherEntity teacher ) {
        this.teacher = teacher;
    }
    public TeacherEntity getTeacher() {
        return this.teacher;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 

}

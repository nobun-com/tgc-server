package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="classes_student")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClassesStudentEntity.countAll", query="SELECT COUNT(x) FROM ClassesStudentEntity x" )
} )
public class ClassesStudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;

    @ManyToOne
    @JoinColumn(name="student_id", referencedColumnName="id")
    private StudentEntity student     ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes     ;

    public ClassesStudentEntity() {
		super();
    }
    
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setStudent( StudentEntity student ) {
        this.student = student;
    }
    public StudentEntity getStudent() {
        return this.student;
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

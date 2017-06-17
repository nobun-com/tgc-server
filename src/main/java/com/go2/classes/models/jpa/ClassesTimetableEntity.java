package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="classes_timetable")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClassesTimetableEntity.countAll", query="SELECT COUNT(x) FROM ClassesTimetableEntity x" )
} )
public class ClassesTimetableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes     ;

    @ManyToOne
    @JoinColumn(name="timetable_id", referencedColumnName="id")
    private TimeTableEntity timeTable   ;

    public ClassesTimetableEntity() {
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

    public void setTimeTable( TimeTableEntity timeTable ) {
        this.timeTable = timeTable;
    }
    public TimeTableEntity getTimeTable() {
        return this.timeTable;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        return sb.toString(); 
    } 
}

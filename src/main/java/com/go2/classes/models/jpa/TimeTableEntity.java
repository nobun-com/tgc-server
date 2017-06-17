package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="time_table")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TimeTableEntity.countAll", query="SELECT COUNT(x) FROM TimeTableEntity x" )
} )
public class TimeTableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @ManyToOne
    @JoinColumn(name="classes_id", referencedColumnName="id")
    private ClassesEntity classes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_time", nullable=false)
    private Date       endTime      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_time", nullable=false)
    private Date       startTime    ;

    @Column(name="status", length=255)
    private String     status       ;

    @OneToMany(mappedBy="timeTable", targetEntity=ClassesTimetableEntity.class)
    private List<ClassesTimetableEntity> listOfClassesTimetable;

    public TimeTableEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public ClassesEntity getClasses() {
		return classes;
	}

	public void setClasses(ClassesEntity classes) {
		this.classes = classes;
	}

	public void setEndTime( Date endTime ) {
        this.endTime = endTime;
    }

	public Date getEndTime() {
        return this.endTime;
    }

    //--- DATABASE MAPPING : start_time ( DATETIME ) 
    public void setStartTime( Date startTime ) {
        this.startTime = startTime;
    }
    public Date getStartTime() {
        return this.startTime;
    }

    //--- DATABASE MAPPING : status ( VARCHAR ) 
    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public void setListOfClassesTimetable( List<ClassesTimetableEntity> listOfClassesTimetable ) {
        this.listOfClassesTimetable = listOfClassesTimetable;
    }
    public List<ClassesTimetableEntity> getListOfClassesTimetable() {
        return this.listOfClassesTimetable;
    }

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(classes);
        sb.append("|");
        sb.append(endTime);
        sb.append("|");
        sb.append(startTime);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 

}

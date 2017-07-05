package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user_booking_order")
public class UserBookingOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id", referencedColumnName="id", nullable=false)
    private StudentEntity student;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="classes_count")
    private Integer classesCount;

    public UserBookingOrderEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

	public Integer getClassesCount() {
		return classesCount;
	}

	public void setClassesCount(Integer classesCount) {
		this.classesCount = classesCount;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append("|");
        sb.append(student);
        sb.append("|");
        sb.append(date);
        return sb.toString(); 
    } 

}
package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user_cart")
public class UserCartEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="timetable_id", referencedColumnName="id")
    private TimeTableEntity timeTable;

    @ManyToOne
    @JoinColumn(name="student_id", referencedColumnName="id")
    private StudentEntity student;
    
    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName="id")
    private UserBookingOrderEntity userBookingOrderEntity;
    
    @Column(name="status", nullable=false, length=10)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable=false)
    private Date date;

    public UserBookingOrderEntity getUserBookingOrderEntity() {
		return userBookingOrderEntity;
	}

	public void setUserBookingOrderEntity(UserBookingOrderEntity userBookingOrderEntity) {
		this.userBookingOrderEntity = userBookingOrderEntity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserCartEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public TimeTableEntity getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTableEntity timeTable) {
		this.timeTable = timeTable;
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
        sb.append(timeTable);
        sb.append("|");
        sb.append(student);
        sb.append("|");
        sb.append(date);
        return sb.toString(); 
    } 

}

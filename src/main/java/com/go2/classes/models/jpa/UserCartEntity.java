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
    @JoinColumn(name="timetable_id", nullable=false, referencedColumnName="id")
    private TimeTableEntity timeTable;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName="id")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName="id")
    private UserBookingOrderEntity userBookingOrder;
    
    @Column(name="status", nullable=false, length=10)
    private String status;

    @ManyToOne
    @JoinColumn(name="coupon_code", referencedColumnName="code")
    private CouponEntity couponEntity;
    
    @Column(name="final_cost", nullable=false)
    private Double finalCost;

    @Column(name="fees", nullable=false)
    private Double fees;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable=false)
    private Date date;

    public UserBookingOrderEntity getUserBookingOrder() {
		return userBookingOrder;
	}

	public void setUserBookingOrder(UserBookingOrderEntity userBookingOrder) {
		this.userBookingOrder = userBookingOrder;
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

	public CouponEntity getCouponEntity() {
		return couponEntity;
	}

	public void setCouponEntity(CouponEntity couponEntity) {
		this.couponEntity = couponEntity;
	}

	public Double getFinalCost() {
		return finalCost;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}

	public void setFinalCost(Double finalCost) {
		this.finalCost = finalCost;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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
        sb.append(user);
        sb.append("|");
        sb.append(date);
        return sb.toString(); 
    } 

}

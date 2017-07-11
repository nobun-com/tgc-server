package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="classes")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ClassesEntity.countAll", query="SELECT COUNT(x) FROM ClassesEntity x" )
} )
public class ClassesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="class_name", nullable=false, length=50)
    private String className;

    @Column(name="about", length=500)
    private String about;
    
    @ManyToOne
    @JoinColumn(name="teacher_id", referencedColumnName="id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name="center_id", referencedColumnName="id")
    private CenterEntity center;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date createdTime;

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", nullable=false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name="end_date")
    private Date endDate;

    @Column(name="fee", nullable=false)
    private Double fee;
    
    @Column(name="occurrence")
    private Integer occurrence;
    
    @Column(name="frequency", nullable=false)
    private String frequency;
    
    @Column(name="frequency_metadata", length=800)
    private String frequencyMetadata;

    @Column(name="logo_url", length=100)
    private String logoUrl;

    @Column(name="rrule", length=255)
    private String rrule;

    @Column(name="min_age", nullable=false)
    private Integer minAge;
    
    @Column(name="max_age", nullable=false)
    private Integer maxAge;
    
    @Column(name="max_slots", nullable=false)
    private Integer maxSlots;
    
    @Column(name="slots_available", nullable=false)
    private Integer slotsAvailable;
    
    @Column(name="category_id", nullable=false)
    private Integer categoryId;
    
    @Column(name="status", nullable=false, length=10)
    private String status = "valid";

    public ClassesEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public void setAbout( String about ) {
        this.about = about;
    }
    public String getAbout() {
        return this.about;
    }

    public void setClassName( String className ) {
        this.className = className;
    }
    public String getClassName() {
        return this.className;
    }

    //--- DATABASE MAPPING : created_time ( DATETIME ) 
    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    //--- DATABASE MAPPING : end_date ( DATETIME ) 
    public void setEndDate( Date endDate ) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }

    //--- DATABASE MAPPING : fee ( DOUBLE ) 
    public void setFee( Double fee ) {
        this.fee = fee;
    }
    public Double getFee() {
        return this.fee;
    }

    //--- DATABASE MAPPING : logo_url ( VARCHAR ) 
    public void setLogoUrl( String logoUrl ) {
        this.logoUrl = logoUrl;
    }
    public String getLogoUrl() {
        return this.logoUrl;
    }

    //--- DATABASE MAPPING : rrule ( VARCHAR ) 
    public void setRrule( String rrule ) {
        this.rrule = rrule;
    }
    public String getRrule() {
        return this.rrule;
    }

    //--- DATABASE MAPPING : start_date ( DATETIME ) 
    public void setStartDate( Date startDate ) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }

    public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public Integer getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Integer occurrence) {
		this.occurrence = occurrence;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyMetadata() {
		return frequencyMetadata;
	}

	public void setFrequencyMetadata(String frequencyMetadata) {
		this.frequencyMetadata = frequencyMetadata;
	}

    public CenterEntity getCenter() {
		return center;
	}

	public void setCenter(CenterEntity center) {
		this.center = center;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getMaxSlots() {
		return maxSlots;
	}

	public void setMaxSlots(Integer maxSlots) {
		this.maxSlots = maxSlots;
	}

    public Integer getSlotsAvailable() {
		return slotsAvailable;
	}

	public void setSlotsAvailable(Integer slotsAvailable) {
		this.slotsAvailable = slotsAvailable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(about);
        sb.append("|");
        sb.append(className);
        sb.append("|");
        sb.append(createdTime);
        sb.append("|");
        sb.append(endDate);
        sb.append("|");
        sb.append(fee);
        sb.append("|");
        sb.append(logoUrl);
        sb.append("|");
        sb.append(rrule);
        sb.append("|");
        sb.append(startDate);
        return sb.toString(); 
    } 

}

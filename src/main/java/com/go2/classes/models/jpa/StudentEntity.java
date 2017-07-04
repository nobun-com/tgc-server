package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="student")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="StudentEntity.countAll", query="SELECT COUNT(x) FROM StudentEntity x" )
} )
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @Column(name="email", nullable=false, unique=true, length=60)
    private String     email        ;

    @Column(name="name", nullable=false, length=20)
    private String name    ;

    @Column(name="gender", length=6)
    private String     gender       ;

    @Column(name="password", nullable=false, length=20)
    private String     password     ;

    @Column(name="phone", length=12)
    private String     phone        ;

    @Column(name="refrralCode", length=12)
    private String refrralCode;
    
    public StudentEntity() {
		super();
    }
    
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    public void setCreatedTime( Date createdTime ) {
        this.createdTime = createdTime;
    }
    public Date getCreatedTime() {
        return this.createdTime;
    }

    //--- DATABASE MAPPING : email ( VARCHAR ) 
    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getFirstName() {
        return this.name;
    }

    //--- DATABASE MAPPING : gender ( VARCHAR ) 
    public void setGender( String gender ) {
        this.gender = gender;
    }
    public String getGender() {
        return this.gender;
    }

    //--- DATABASE MAPPING : password ( VARCHAR ) 
    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    //--- DATABASE MAPPING : phone ( VARCHAR ) 
    public void setPhone( String phone ) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    public String getRefrralCode() {
		return refrralCode;
	}

	public void setRefrralCode(String refrralCode) {
		this.refrralCode = refrralCode;
	}

    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(createdTime);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(gender);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(phone);
        return sb.toString(); 
    } 

}

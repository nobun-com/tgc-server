package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

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

    @Column(name="first_name", nullable=false, length=20)
    private String     firstName    ;

    @Column(name="gender", length=6)
    private String     gender       ;

    @Column(name="last_name", length=20)
    private String     lastName     ;

    @Column(name="password", nullable=false, length=20)
    private String     password     ;

    @Column(name="phone", length=12)
    private String     phone        ;

    @Column(name="refrralCode", length=12)
    private String refrralCode;
    
    @OneToMany(mappedBy="student", targetEntity=ClassesStudentEntity.class)
    private List<ClassesStudentEntity> listOfClassesStudent;

    @OneToMany(mappedBy="student", targetEntity=StudentClassesEntity.class)
    private List<StudentClassesEntity> listOfStudentClasses;

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

    //--- DATABASE MAPPING : first_name ( VARCHAR ) 
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    //--- DATABASE MAPPING : gender ( VARCHAR ) 
    public void setGender( String gender ) {
        this.gender = gender;
    }
    public String getGender() {
        return this.gender;
    }

    //--- DATABASE MAPPING : last_name ( VARCHAR ) 
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
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

	public void setListOfClassesStudent( List<ClassesStudentEntity> listOfClassesStudent ) {
        this.listOfClassesStudent = listOfClassesStudent;
    }
    public List<ClassesStudentEntity> getListOfClassesStudent() {
        return this.listOfClassesStudent;
    }

    public void setListOfStudentClasses( List<StudentClassesEntity> listOfStudentClasses ) {
        this.listOfStudentClasses = listOfStudentClasses;
    }
    public List<StudentClassesEntity> getListOfStudentClasses() {
        return this.listOfStudentClasses;
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
        sb.append(firstName);
        sb.append("|");
        sb.append(gender);
        sb.append("|");
        sb.append(lastName);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(phone);
        return sb.toString(); 
    } 

}

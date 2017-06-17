package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="teacher")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TeacherEntity.countAll", query="SELECT COUNT(x) FROM TeacherEntity x" )
} )
public class TeacherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @Column(name="email", length=60)
    private String     email        ;

    @Column(name="first_name", nullable=false, length=20)
    private String     firstName    ;

    @Column(name="gender", length=6)
    private String     gender       ;

    @Column(name="last_name", nullable=false, length=20)
    private String     lastName     ;

    @Column(name="password", nullable=false, length=20)
    private String     password     ;

    @Column(name="phone", length=12)
    private String     phone        ;

    @Column(name="profile_image_url", length=500)
    private String profileImageUrl;

    @OneToMany(mappedBy="teacher", targetEntity=TeacherClassesEntity.class)
    private List<TeacherClassesEntity> listOfTeacherClasses;

    @OneToMany(mappedBy="teacher", targetEntity=ClassesTeacherEntity.class)
    private List<ClassesTeacherEntity> listOfClassesTeacher;

    public TeacherEntity() {
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

    public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

    public void setListOfTeacherClasses( List<TeacherClassesEntity> listOfTeacherClasses ) {
        this.listOfTeacherClasses = listOfTeacherClasses;
    }
    public List<TeacherClassesEntity> getListOfTeacherClasses() {
        return this.listOfTeacherClasses;
    }

    public void setListOfClassesTeacher( List<ClassesTeacherEntity> listOfClassesTeacher ) {
        this.listOfClassesTeacher = listOfClassesTeacher;
    }
    public List<ClassesTeacherEntity> getListOfClassesTeacher() {
        return this.listOfClassesTeacher;
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

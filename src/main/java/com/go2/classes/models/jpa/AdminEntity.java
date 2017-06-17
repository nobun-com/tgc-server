package com.go2.classes.models.jpa;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="admin")
@NamedQueries ( {
  @NamedQuery ( name="AdminEntity.countAll", query="SELECT COUNT(x) FROM AdminEntity x" )
} )
public class AdminEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time")
    private Date       createdTime  ;

    @Column(name="email", length=60)
    private String     email        ;

    @Column(name="password", nullable=false, length=20)
    private String     password     ;

    @OneToMany(mappedBy="admin", targetEntity=CenterEntity.class)
    private List<CenterEntity> listOfCenter;

    public AdminEntity() {
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

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setListOfCenter( List<CenterEntity> listOfCenter ) {
        this.listOfCenter = listOfCenter;
    }
    public List<CenterEntity> getListOfCenter() {
        return this.listOfCenter;
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
        sb.append(password);
        return sb.toString(); 
    } 

}

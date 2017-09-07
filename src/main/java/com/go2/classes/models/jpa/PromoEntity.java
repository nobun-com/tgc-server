/*
 * Created on 17 Jul 2017 ( Time 15:05:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.go2.classes.models.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "promo"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "promo")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PromoEntity.countAll", query = "SELECT COUNT(x) FROM PromoEntity x") })
public class PromoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "promo_image_four", length = 600)
	private String promoImageFour;

	@Column(name = "promo_image_one", length = 600)
	private String promoImageOne;

	@Column(name = "promo_image_three", length = 600)
	private String promoImageThree;

	@Column(name = "promo_image_two", length = 600)
	private String promoImageTwo;

	@Column(name = "promo_url_four", length = 600)
	private String promoUrlFour;

	@Column(name = "promo_url_one", length = 600)
	private String promoUrlOne;

	@Column(name = "promo_url_three", length = 600)
	private String promoUrlThree;

	@Column(name = "promo_url_two", length = 600)
	private String promoUrlTwo;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PromoEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPromoImageFour() {
		return promoImageFour;
	}

	public void setPromoImageFour(String promoImageFour) {
		this.promoImageFour = promoImageFour;
	}

	public String getPromoImageOne() {
		return promoImageOne;
	}

	public void setPromoImageOne(String promoImageOne) {
		this.promoImageOne = promoImageOne;
	}

	public String getPromoImageThree() {
		return promoImageThree;
	}

	public void setPromoImageThree(String promoImageThree) {
		this.promoImageThree = promoImageThree;
	}

	public String getPromoImageTwo() {
		return promoImageTwo;
	}

	public void setPromoImageTwo(String promoImageTwo) {
		this.promoImageTwo = promoImageTwo;
	}

	public String getPromoUrlFour() {
		return promoUrlFour;
	}

	public void setPromoUrlFour(String promoUrlFour) {
		this.promoUrlFour = promoUrlFour;
	}

	public String getPromoUrlOne() {
		return promoUrlOne;
	}

	public void setPromoUrlOne(String promoUrlOne) {
		this.promoUrlOne = promoUrlOne;
	}

	public String getPromoUrlThree() {
		return promoUrlThree;
	}

	public void setPromoUrlThree(String promoUrlThree) {
		this.promoUrlThree = promoUrlThree;
	}

	public String getPromoUrlTwo() {
		return promoUrlTwo;
	}

	public void setPromoUrlTwo(String promoUrlTwo) {
		this.promoUrlTwo = promoUrlTwo;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(createdTime);
		sb.append("|");
		sb.append(promoImageFour);
		sb.append("|");
		sb.append(promoImageOne);
		sb.append("|");
		sb.append(promoImageThree);
		sb.append("|");
		sb.append(promoImageTwo);
		sb.append("|");
		sb.append(promoUrlFour);
		sb.append("|");
		sb.append(promoUrlOne);
		sb.append("|");
		sb.append(promoUrlThree);
		sb.append("|");
		sb.append(promoUrlTwo);
		return sb.toString();
	}

}

/*
 * Java bean class for entity table slider 
 * Created on 28 Aug 2017 ( Date ISO 2017-08-28 - Time 15:08:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.go2.classes.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity bean for table "slider"
 * 
 * @author Telosys Tools Generator
 *
 */
public class Slider implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // Primary Key

	private Date createdTime;
	private String imageUrl;
	private String title;
	private String description;
	private String urlTitle;
	private String url;

	/**
	 * Default constructor
	 */
	public Slider() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR THE PRIMARY KEY
	// ----------------------------------------------------------------------
	/**
	 * Set the "id" field value This field is mapped on the database column "id"
	 * ( type "BIGINT", NotNull : true )
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the "id" field value This field is mapped on the database column "id"
	 * ( type "BIGINT", NotNull : true )
	 * 
	 * @return the field value
	 */
	public Long getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR DATA FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : created_time ( DATETIME )
	/**
	 * Set the "createdTime" field value This field is mapped on the database
	 * column "created_time" ( type "DATETIME", NotNull : false )
	 * 
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * Get the "createdTime" field value This field is mapped on the database
	 * column "created_time" ( type "DATETIME", NotNull : false )
	 * 
	 * @return the field value
	 */
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

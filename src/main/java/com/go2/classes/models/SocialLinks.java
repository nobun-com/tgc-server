/*
 * Java bean class for entity table social_links 
 * Created on 26 Aug 2017 ( Date ISO 2017-08-26 - Time 13:07:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.go2.classes.models;

import java.io.Serializable;

public class SocialLinks implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // Primary Key

	private String contactEmail;
	private String facebookUrl;
	private String instagramUrl;
	private String senderEmail;

	/**
	 * Default constructor
	 */
	public SocialLinks() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR THE PRIMARY KEY
	// ----------------------------------------------------------------------

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR DATA FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : contact_email ( VARCHAR )

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	// --- DATABASE MAPPING : facebook_url ( VARCHAR )

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getFacebookUrl() {
		return this.facebookUrl;
	}

	// --- DATABASE MAPPING : instagram_url ( VARCHAR )

	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	public String getInstagramUrl() {
		return this.instagramUrl;
	}

	// --- DATABASE MAPPING : sender_email ( VARCHAR )

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderEmail() {
		return this.senderEmail;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("|");
		sb.append(contactEmail);
		sb.append("|");
		sb.append(facebookUrl);
		sb.append("|");
		sb.append(instagramUrl);
		sb.append("|");
		sb.append(senderEmail);
		return sb.toString();
	}

}


package com.go2.classes.models.jpa;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "social_links")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "SocialLinksEntity.countAll", query = "SELECT COUNT(x) FROM SocialLinksEntity x") })
public class SocialLinksEntity implements Serializable {

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
	@Column(name = "contact_email", length = 600)
	private String contactEmail;

	@Column(name = "facebook_url", length = 600)
	private String facebookUrl;

	@Column(name = "instagram_url", length = 600)
	private String instagramUrl;

	@Column(name = "sender_email", length = 600)
	private String senderEmail;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public SocialLinksEntity() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
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
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
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

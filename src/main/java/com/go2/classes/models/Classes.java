package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Classes implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	private String className;

	@Size(max = 500)
	private String about;

	@NotNull
	private Long educatorId;

	@NotNull
	private Long centerId;

	private Date createdTime;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	@NotNull
	private Double fee;

	private Integer occurrence;

	@Size(max = 10)
	private String frequency;

	@Size(max = 800)
	private String frequencyMetadata;

	@Size(max = 100)
	private String logoUrl;

	@Size(max = 255)
	private String rrule;

	private Integer minAge;

	private Integer maxAge;

	private Integer maxSlots;

	private Integer slotsAvailable;

	private Integer categoryId;

	private String importantNotes;

	public void setId(Long id) {
		this.id = id;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Long getEducatorId() {
		return educatorId;
	}

	public void setEducatorId(Long educatorId) {
		this.educatorId = educatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getRrule() {
		return rrule;
	}

	public void setRrule(String rrule) {
		this.rrule = rrule;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
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

	public String getImportantNotes() {
		return importantNotes;
	}

	public void setImportantNotes(String importantNotes) {
		this.importantNotes = importantNotes;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("|");
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
		sb.append("|");
		sb.append(importantNotes);
		return sb.toString();
	}
}

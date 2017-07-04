package com.go2.classes.models;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String preview;

    private String category;

    private String thumbnailUrl;

    private String imageUrl;

    private String body;

    private Date postDate;

    private Boolean published;

    private Boolean featured;

    private Date createdDate;
    
    public Article() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(title);
        sb.append("|");
        sb.append(preview);
        sb.append("|");
        sb.append(imageUrl);
        sb.append("|");
        sb.append(category);
        sb.append("|");
        sb.append(body);
        sb.append("|");
        sb.append(published);
        sb.append("|");
        sb.append(featured);
        sb.append("|");
        sb.append(postDate);
        sb.append("|");
        sb.append(createdDate);
        return sb.toString(); 
    } 
}

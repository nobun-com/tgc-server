package com.go2.classes.models.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="articles")
public class ArticlesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="title", nullable=false, length=255)
    private String title;

    @Column(name="preview", nullable=false, length=100)
    private String preview;

    @Column(name="category", nullable=false, length=100)
    private String category;

    @Column(name="thumbnail_url", nullable=false, length=100)
    private String thumbnailUrl;

    @Column(name="image_url", nullable=false, length=100)
    private String imageUrl;

    @Column(name="body", nullable=false, length=100)
    private String body;

    @Column(name="published", nullable=false, length=100)
    private Boolean published;

    @Column(name="featured", nullable=false, length=100)
    private Boolean featured;

    @Column(name="post_date", nullable=false, length=100)
    private Date postDate;

    @Column(name="created_date", nullable=false, length=100)
    private Date createdDate;

    public ArticlesEntity(){}
    
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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

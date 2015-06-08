package com.myapp.rssmodels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class Message implements Serializable{

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "Subject")
	private String subject;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Link")
	private String link;
	
	@Column(name = "Category")
	private String category;
	
	@Column(name = "PubDate")
	private String pubDate;
	
	public Message(){
		super();
	}
	
	public Message(String subject, String description, 
			String link, String category, String pubDate){
		this.subject = subject;
		this.description = description;
		this.link = link;
		this.category = category;
		this.pubDate = pubDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String toString(){
		return "FeedMessage [title=" + subject + ", description=" + description
		        + ", link=" + link + ", category=" + category + 
		        ", publishedOn=" + pubDate + "]";
	}
	
}

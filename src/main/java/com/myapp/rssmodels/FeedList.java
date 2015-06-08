package com.myapp.rssmodels;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FeedList {

	private String title;
	private String description;
	private String link;
	private Date pubDate;
	
	private List<Message> messages = new ArrayList<Message>();
	
	public FeedList(){
		super();
	}
	
	public FeedList(String title, String description, Date pubDate){
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		
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

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public List<Message> getMessages() {
		return messages;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void addMessage(Message message){
		this.messages.add(message);
	}
}

package com.myapp.services;

import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.daos.MessageDAO;
import com.myapp.rssmodels.FeedList;
import com.myapp.rssmodels.Message;
import com.myapp.rssutilss.RSSParser;

@Service
@Transactional
public class RSSService {
	
	protected static Logger logger = LoggerFactory.getLogger(RSSService.class);
	
	@Autowired
	private MessageDAO messageDao;
	
	private RSSParser rssParser;
	
	/*public RSSService(){
		super();
		feeds = new FeedList();
	}*/
	
	public boolean checkURIPath(String xmlPath){
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(xmlPath);
	}
	
	public void readFeed(String xmlPath)throws Exception{
		rssParser = new RSSParser(xmlPath);
		FeedList feeds = rssParser.readFeed();
		if(feeds != null)
			saveFeed(feeds);
	}
	
	public void saveFeed(FeedList feeds){
		for(Message m : feeds.getMessages()){
			messageDao.addMessage(m);
		}
	}
	
	public List<Message> getFeeds(){
		return messageDao.getAllMessages();
//		return feeds;
	}
	
	public List<Message> getFeeds(int index, int length){
		return messageDao.getMessages(index, length);
	}
	
	public Long totalMessages(){
		return messageDao.totalMessages();
	}
}

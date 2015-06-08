package com.myapp.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.rssmodels.Message;

@Repository
public class MessageDAO {

	protected static Logger logger = LoggerFactory.getLogger(MessageDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Message> getAllMessages() {
		Session session = sessionFactory.getCurrentSession();		
		Query q = session.createQuery("select g from Message g order by id desc");
		List<Message> messages = q.list(); 
		return messages;			
	}
 
	public void addMessage(Message message) {
		Session session = sessionFactory.getCurrentSession();	
		session.save(message);
	}
	
	public List<Message> getMessages(int index, int length){
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select g from Message g order by id desc");
		q = q.setFirstResult(index).setMaxResults(length);
		List<Message> messages = q.list();
		return messages;
	}
	
	public Long totalMessages(){
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select count(*) from Message");
		return (Long)q.uniqueResult();
	}
}

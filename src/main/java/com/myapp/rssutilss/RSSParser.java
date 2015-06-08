package com.myapp.rssutilss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.myapp.rssmodels.FeedList;
import com.myapp.rssmodels.Message;

public class RSSParser {

	private URI URI;

	public RSSParser(String URL)throws Exception{
		try {
			this.URI = new URI(URL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new Exception("Invalid URL");
		}
	}

	public FeedList readFeed()throws Exception{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(read());
			doc.getDocumentElement().normalize();
			if(doc.getDocumentElement().getNodeName().equals(GlobalUtils.RSS) &&
					doc.getElementsByTagName(GlobalUtils.CHANNEL).getLength() == 1){
				NodeList nList = doc.getElementsByTagName(GlobalUtils.ITEM);
				return readItems(nList);
			}else{
				throw new Exception("XML is not RSS");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	private FeedList readItems(NodeList nList){
		FeedList feeds = new FeedList();
		for(int i = 0; i < nList.getLength(); i++){
			Node n = nList.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				String subject = e.getElementsByTagName(GlobalUtils.TITLE).item(0).getTextContent();
				String link = e.getElementsByTagName(GlobalUtils.LINK).item(0).getTextContent();
				String desc = e.getElementsByTagName(GlobalUtils.DESCRIPTION).item(0).getTextContent();
				String category = e.getElementsByTagName(GlobalUtils.CATEGORY).item(0).getTextContent();
				String pubDate = e.getElementsByTagName(GlobalUtils.PUB_DATE).item(0).getTextContent();
				Message m = new Message(subject, desc, link, category, pubDate);
				feeds.addMessage(m);
			}
		}
		return feeds;
	}

	private InputStream read() {
		try { 
			return URI.toURL().openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

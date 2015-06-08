package com.myapp.rssapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.rssmodels.Message;
import com.myapp.services.RSSService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private RSSService rssService;

	@Autowired
	public HomeController(RSSService rssService){
		this.rssService = rssService;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("messages", rssService.getFeeds());
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}

	@RequestMapping(value = "newfeed", method = RequestMethod.POST)
	public String newFeedsPost(@RequestParam("xmlpath") String xmlpath, Locale locale, Model model){
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("Path is: {}", xmlpath);

		try{
			if(rssService.checkURIPath(xmlpath)){
				rssService.readFeed(xmlpath);
			}else{
				throw new Exception("URI is not valid!");
			}
		}catch(Exception e){
			logger.info("{}", e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		return "home";
	}

	@RequestMapping(value = "paginationdatasource", method = RequestMethod.POST)
	public @ResponseBody String paginationdatasource(HttpServletRequest request, HttpServletResponse response, String pageIndex){

		Integer pageNumber = 0;
		if(null != request.getParameter("start"))
			pageNumber = (Integer.valueOf(request.getParameter("start"))/10);

		Integer pageDisplayLength = 10;
		if(null != request.getParameter("length"))
			pageDisplayLength = Integer.parseInt(request.getParameter("length"));

		List<Message> messages = rssService.getFeeds((pageNumber*pageDisplayLength), 
				pageDisplayLength);

		JSONObject json = new JSONObject();
		Long count = rssService.totalMessages();
		try {
			json.put("draw", request.getParameter("draw"));
			json.put("recordsTotal", count);
			json.put("recordsFiltered", count);
			json.put("data", messages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("{}", e.getMessage());
		}
		return json.toString();
	}

}

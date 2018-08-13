package com.wind.security;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/welcome.html")
	public String welcome(Locale locale, Model model) {
		return "security/welcome";
	}
	@RequestMapping("/login.html")
	public String login(Locale locale, Model model) {
		return "security/login";
	}
	
	@RequestMapping("/loginForm.html")
	public String loginForm(Locale locale, Model model) {
		return "security/loginForm";
	}	
	
	@RequestMapping("/index")
	public String index(Locale locale, Model model) {
		return "index";
	}	
	
	@RequestMapping("/notice")
	public String notice(Locale locale, Model model) {
		return "notice";
	}	
	
	@RequestMapping("/notice/notice_a")
	public String notice_a(Locale locale, Model model) {
		return "notice/notice_a";
	}	
	@RequestMapping("/user/user_a")
	public String user_a(Locale locale, Model model) {
		return "user/user_a";
	}		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}

package com.savelife;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	DonorRepoImpl dbImpl;
	
	@Autowired
	MessageSource messageSource;
	
	
	@GetMapping("/login")
	public String login() {
		System.out.println("this is from login controller, I am at login method");
		return "login.html";
	}
	
	@PostMapping("/login")
	public String dologin(@RequestParam("email") String mail,
			@RequestParam("password") String password, Model model, Locale locale, HttpSession session) {
		System.out.println(mail+", "+password);
		
		if("admin@savelife.com".equalsIgnoreCase(mail) && "SaveLife@123".equals(password)) {
			session.setAttribute("adminAct", "mySaveLifeAdminKey");
			return "admin.html";
		}
		
		Donor byEmailAndPassword = dbImpl.findByEmailAndPassword(mail,password);
		if(byEmailAndPassword==null) {
			String message = messageSource.getMessage("login.error.info", null, locale);
			model.addAttribute("warInfo",message);
			return "login.html";
		}
		else {
		return "dashboard.html";
	}}
}

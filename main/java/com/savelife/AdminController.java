package com.savelife;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	MessageSource messageSource;
	@Autowired
	private ContactRequestDao crd;
	
	@GetMapping("/allRequest")
	public String allRequest(Model model, HttpSession session, Locale locale) {
		String adminKey = (String)session.getAttribute("adminAct");
		if(adminKey==null || "".equals(adminKey) || ! "mySaveLifeAdminKey".equals(adminKey)) {
			String message = messageSource.getMessage("login.error.info", null, locale);
			model.addAttribute("warInfo",message);
			return "login.html";
		}
		List<ContactRequest> list = crd.findByIsActiveTrue();
		model.addAttribute("queryList", list);
		return "request-list.html";
	}
	
	@GetMapping("/close/{sno}")
	public String closeRequest(Model model, @PathVariable Long sno, Locale locale, HttpSession session) {
		String adminKey = (String)session.getAttribute("adminAct");
		if(adminKey==null || "".equals(adminKey) || ! "mySaveLifeAdminKey".equals(adminKey)) {
			String message = messageSource.getMessage("login.error.info", null, locale);
			model.addAttribute("warInfo",message);
			return "login.html";
		}
		Optional<ContactRequest> id = crd.findById(sno);
		ContactRequest contactRequest = id.get();
		contactRequest.setActive(false);
		crd.save(contactRequest);
		List<ContactRequest> list = crd.findByIsActiveTrue();
		model.addAttribute("queryList", list);
		model.addAttribute("pFlag", "2"); 
		return "request-list.html";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session, Locale locale) {
		session.removeAttribute("adminAct");
		
		return "login.html";
	}
}

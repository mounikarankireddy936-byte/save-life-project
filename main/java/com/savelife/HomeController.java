package com.savelife;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	
	@Autowired
	private DonorRepo repo;
	
	@Autowired
	DonorRepoImpl dbImpl;
	
	@Autowired
	ContactRequestDao crDao;
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping({"/home","/about"})
	String welcome() {
		System.out.println("Hi");
		return "home.html";
	}
	
	@GetMapping("/contact")
	String contact() {
		return "contact.html";
	}
	
	//@GetMapping("/myRegister")
		@PostMapping("/contact")
		public String contact(@RequestParam("fullName") String fName,
				@RequestParam("email") String mail,
				@RequestParam("mobile") String mobile,
				@RequestParam("description") String description,Model model, Locale locale) {
			
			
			
			ContactRequest contactRequest = new ContactRequest();
			contactRequest.setName(fName);
			contactRequest.setEmail(mail);
			contactRequest.setMobile(Long.valueOf(mobile));
			contactRequest.setActive(true);
			contactRequest.setDescription(description);
			
			System.out.println(contactRequest.toString());
			
			crDao.save(contactRequest);
			String message = messageSource.getMessage("info.contact.us.submit.success", null, locale);

			model.addAttribute("successInfo", message);
			
				
			return "contact.html";
		}
		
	
	@GetMapping("/")
	String welcome2() {
		System.out.println("Hi from 2");
		return "home.html";
	}
	
	@GetMapping({"/registration","/reg"})
	String registration() {
		System.out.println("Hi I am registration or reg");
		return "registration.html";
	}
	
	@GetMapping("/search")
	String search(){
		System.out.println("I am in Search");
		return "search";
	}
	@PostMapping("/search")
	public String mySearch(@RequestParam("bGroup") String bGroup, Model model) {
		
		System.out.println("bGroup : "+bGroup);
		
		// check in DB 
		
		
		List<Donor> allDonors = dbImpl.findAll(); // fetch from DB (CRUD) Read
		
		if(!"".equalsIgnoreCase(bGroup)) {
			allDonors = allDonors.stream().filter(d->bGroup.equalsIgnoreCase(d.getBloodGroup())).collect(Collectors.toList());
		}
		
		model.addAttribute("donorList", allDonors);
		model.addAttribute("pFlag", "2");
		model.addAttribute("bGroup", "".equalsIgnoreCase(bGroup)?"All":bGroup);
			
		return "search";
	}
	
	
	//@GetMapping("/myRegister")
	@PostMapping("/registration")
	public String myReg(@RequestParam("fullName") String fName,
			@RequestParam("email") String mail,
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String cnfPassword,
			@RequestParam("mobile") String mobile,
			@RequestParam("bloodGroup") String bloodGroup,Model model, Locale locale) {
		
		
		Donor byEmail = dbImpl.findByEmail(mail);
		
		if(byEmail==null || byEmail.getEmail()==null || !mail.equalsIgnoreCase(byEmail.getEmail())) {
		
		Donor donorUser = new Donor();
		donorUser.setDonorName(fName);
		donorUser.setEmail(mail);
		donorUser.setPassword(password);
		donorUser.setMobile(Long.valueOf(mobile));
		donorUser.setBloodGroup(bloodGroup);
		
		System.out.println(donorUser.toString());
		
		Donor save = dbImpl.save(donorUser);
		String message = messageSource.getMessage("info.reg.submit.success", null, locale);

		model.addAttribute("successInfo", message);
		}
		else {
			String message = messageSource.getMessage("info.reg.submit.already.exist", null, locale);

			model.addAttribute("warInfo", message);

		}
			
		return "registration.html";
	}
	
	

}

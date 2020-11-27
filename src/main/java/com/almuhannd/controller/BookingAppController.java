package com.almuhannd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almuhannd.model.UserBookingModel;
import com.almuhannd.repository.UserBookingRepository;
import com.almuhannd.service.UserBookingService;

@Controller
public class BookingAppController {
	

	@Autowired
	private UserBookingService service;
	
	@Autowired
	private UserBookingRepository repo;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/bookingPage")
	public String showForm(Model model) {
		model.addAttribute("booking",new UserBookingModel());
	    return "bookingPage";
	}
	
	@PostMapping("/posting")
	public String saveOrder(@Valid @ModelAttribute("booking") UserBookingModel booking, BindingResult bindingResult,
							RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return "bookingPage";
		}
		
		redirectAttributes.addFlashAttribute("message", "Your booking is successfullly made, we will contact us soon");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		service.saveOrder(booking);
		return "redirect:/bookingPage";
	}
	
	@GetMapping("/admin")
	public String getData(Model model, @RequestParam(value = "page",required = false) Integer p, @RequestParam(defaultValue = "") String name) {
		int perPage = 5;
		int page = (p != null) ? p : 0;
		Pageable pageable = PageRequest.of(page, perPage);
		
		Page<UserBookingModel> bookings = repo.findByUsernameLikeOrderByIdDesc(pageable,"%"+name+"%");
		
//		List <UserBookingModel> bookings = service.findByName(name);
		int annualEarning = service.getAnnualEarning();
		int monthyEarning = service.getMonthyEarning();
		long count = service.getEntries();
		int todayTask = service.getTodayTasks();
		
		model.addAttribute("annualEarning",annualEarning );
		model.addAttribute("monthyEarning",monthyEarning );
		model.addAttribute("count", count);
		model.addAttribute("todayTask", todayTask);
		model.addAttribute("bookings", bookings);
		
		long countBooking = repo.count();
		double pageCount = Math.ceil((double)countBooking/(double)perPage);
		
		model.addAttribute("pageCount",(int)pageCount );
		model.addAttribute("perPage", perPage);
		model.addAttribute("countBooking", countBooking);
		model.addAttribute("page", page);
		
		
		
		return "dashboard";
	}
	
	

}

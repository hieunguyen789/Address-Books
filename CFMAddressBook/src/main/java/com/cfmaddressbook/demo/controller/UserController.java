package com.cfmaddressbook.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfmaddressbook.demo.entities.AddressBooks;
import com.cfmaddressbook.demo.entities.User;
import com.cfmaddressbook.demo.services.AddressBooksService;
import com.cfmaddressbook.demo.services.UserService;
@Controller
@RequestMapping(value = "/api")
public class UserController {
	
	@Autowired
	AddressBooksService addressBooksService; 
	
	@Autowired
	UserService userService; 
	
	
	@RequestMapping("")
	public String showIndex() {
		return "/index"; 
	}
	
	@GetMapping("login")
	public String showLoginPage() {
		
		return"login";
	}

	
	@RequestMapping("list_all_addressbooks")
	public ModelAndView showAllAddress() {
		List<AddressBooks> listAddressBooks = addressBooksService.findAllAddressBooks();
		ModelAndView mav = new ModelAndView("list_all_addressbooks");
		mav.addObject("listAllAddressBooks",listAddressBooks);
		return mav; 
	}
	
	
	
	
	@RequestMapping("/register")
	public String showRegisterUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register_user";
		
		
	}
	
	@RequestMapping(value ="/register/save", method= RequestMethod.POST)
	public String saveUserProcess(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String encodePassword = encode.encode(user.getPassword());
		user.setPassword(encodePassword);
		user.setEnable(true);
		
		if (bindingResult.hasErrors()) {
			return "redirect:/api/register";
		}
		
		if(userService.findByEmail(user.getEmail()) == null){
			userService.saveUser(user);
			return"redirect:/api";
		}else {
			return "exist_email";
		}
	}
	
	@RequestMapping("/search_addressbooks_by_name")
	public String searchAddressBooksByName(@RequestParam("name") String name, Model model) {
		List<AddressBooks> listAddressBooks = addressBooksService.findByKeyword(name);
		model.addAttribute("listAddressBooks",listAddressBooks);
		return "/search_addressbooks_by_name";
	}
	
	@RequestMapping("order_by_name")
	public ModelAndView orderByName() {
		List<AddressBooks> list = addressBooksService.filterByName();
		ModelAndView mav = new ModelAndView("order_by_name");
		mav.addObject("listAllAddressBooks", list);
		return mav; 
	}
	
	@RequestMapping("/logout")
	public String showLogout() {
		return "logout";
	}
	
	@RequestMapping("/most_living_city")
	public String showMostLivingCity(Model model) {
		List<AddressBooks> list = addressBooksService.findAllAddressBooks(); 
		
		int maxCount = 0;
		String state = "";
		for(int i =0; i < list.size(); i++){
			int count = 0;
			String str = list.get(i).getState();
			for(int j=0; j<list.size();j++) {
				if(str.equals(list.get(j).getState())) {
					count++;
				}
			}
			if(count > maxCount) {
				maxCount = count;
				state = str;
			}
		}
		List<AddressBooks> addressbooks = addressBooksService.findByState(state);
		model.addAttribute("listAllAddressBooks",addressbooks);
		
		return "most_living_city";
	}

	
}

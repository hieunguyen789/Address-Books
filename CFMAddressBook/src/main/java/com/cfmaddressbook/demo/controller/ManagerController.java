package com.cfmaddressbook.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cfmaddressbook.demo.entities.AddressBooks;
import com.cfmaddressbook.demo.services.AddressBooksService;

@Controller
@RequestMapping(value = "manager/api")
public class ManagerController {

	@Autowired
	private AddressBooksService addressBooksService;

	@RequestMapping("/register_addressbooks")
	public String viewRegisterAddress(Model model) {
		AddressBooks addressBooks = new AddressBooks();
		model.addAttribute("addressbooks", addressBooks);
		return "register_addressbooks";
	}

	@RequestMapping("admin_list_all_addressbooks")
	public ModelAndView showAdminAllAddress() {
		List<AddressBooks> listAddressBooks = addressBooksService.findAllAddressBooks();
		ModelAndView mav = new ModelAndView("admin_list_all_addressbooks");
		mav.addObject("listAllAddressBooks", listAddressBooks);
		return mav;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String addAddressBooks(@Valid @ModelAttribute("addressbooks") AddressBooks addressBooks, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/manager/api/register_addressbooks";
		} else {
			addressBooksService.saveAddressBooks(addressBooks);
			return "redirect:/manager/api/admin_list_all_addressbooks";
		}
	}

	@RequestMapping("edit_addressbooks/{id}")
	public ModelAndView editAddressBooks(@PathVariable("id") int id) {
		AddressBooks existAddressBooks = addressBooksService.findById(id);
		ModelAndView mav = new ModelAndView("edit_addressbooks");
		mav.addObject("addressbooks", existAddressBooks);
		return mav;
	}

	@RequestMapping("edit_addressbooks_process")
	public String editAddressBooksProcess(@ModelAttribute("addressbooks") AddressBooks addressBooks) {
		AddressBooks existAddressBooks = addressBooksService.findByEmail(addressBooks.getEmail());
		existAddressBooks.setCity(addressBooks.getCity());
		existAddressBooks.setEmail(addressBooks.getEmail());
		existAddressBooks.setName(addressBooks.getName());
		existAddressBooks.setHomePhone(addressBooks.getHomePhone());
		existAddressBooks.setState(addressBooks.getState());
		existAddressBooks.setStreet(addressBooks.getStreet());
		existAddressBooks.setZipcode(addressBooks.getZipcode());
		addressBooksService.saveAddressBooks(existAddressBooks);
		return "redirect:/manager/api/admin_list_all_addressbooks";
	}

	@RequestMapping("delete_addressbooks/{id}")
	public String deleteAddressById(@PathVariable(name = "id") int id) {
		addressBooksService.deleteById(id);
		return "redirect:/manager/api/admin_list_all_addressbooks";
	}

}

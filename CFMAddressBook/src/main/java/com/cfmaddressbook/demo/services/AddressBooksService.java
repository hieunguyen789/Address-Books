package com.cfmaddressbook.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfmaddressbook.demo.entities.AddressBooks;
import com.cfmaddressbook.demo.repositories.AddressBooksRepository;

@Service
public class AddressBooksService {
	@Autowired
	private AddressBooksRepository addressBooksRepos;

	public void saveAddressBooks(AddressBooks addressBooks) {
		addressBooksRepos.save(addressBooks);
	}

	public List<AddressBooks> findAllAddressBooks() {
		return addressBooksRepos.findAll();
	}

	public AddressBooks findByName(String name) {

		return addressBooksRepos.findByName(name);
	}

	public List<AddressBooks> findByKeyword(String key) {
		List<AddressBooks> list = addressBooksRepos.findAll();
		List<AddressBooks> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().contains(key)) {

				result.add(list.get(i));
			}
		}
		return result;
	}
	public List<AddressBooks> filterByName(){
		return addressBooksRepos.filterByName();
	}
	
	public AddressBooks findByEmail(String email) {
		if (email == null || email.length() == 0) {
			return addressBooksRepos.findByEmail(email);
		}
		return addressBooksRepos.findByEmail(email);
	}
	
	public AddressBooks findById(int id) {
		if(id < 0) {
			return addressBooksRepos.findById(id); 
		}
		return addressBooksRepos.findById(id); 
	}
	

	public void deleteById(int id) {
		addressBooksRepos.deleteById(id);
	}

	public List<AddressBooks> findByState(String state){
		
		return addressBooksRepos.findByState(state);
	}
}

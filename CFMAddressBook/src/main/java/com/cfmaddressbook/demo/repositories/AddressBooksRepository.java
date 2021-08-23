package com.cfmaddressbook.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cfmaddressbook.demo.entities.AddressBooks;

public interface AddressBooksRepository extends JpaRepository<AddressBooks, Integer>{
	
	public List<AddressBooks> findAll();
	
	public AddressBooks findByName(String name);
	
	public AddressBooks findByEmail(String email);
	
	public AddressBooks findById(int id);
	
	public List<AddressBooks> findByState(String state);
	



	@Query("SELECT n FROM AddressBooks n ORDER BY n.name")
	public List<AddressBooks> filterByName();
	
	public void deleteByName(String name);
	
}
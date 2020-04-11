package com.spring.microservice.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

// category service created to connect to the repository and to be able to use special methods to query
@Service
public class CategoryService {

	// connecting to the repository
	@Autowired
	private CategoryRepository categoryRepository;
	
	// select all category
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	// optional is used to be able to use the isPresent() method
	public Optional<Category> getById(final int id) {
		return categoryRepository.findById(id);
	}
	
	// updating the category with the category id and saving it
	public Category updateCategory(final int id, final Category category) {
		return categoryRepository.save(category);
	}
	
	// saving the category
	public Category saveCategory(final Category category) {
		return categoryRepository.save(category);
	}
	
	// deleting the category
	public void deleteCategory(final int id) {
		categoryRepository.deleteById(id);
	}
	
	// finding the category by id - used for the update
	public Category findCategoryById(final int id) {
		return categoryRepository.findCategoryById(id);
	}
	
}

package com.spring.microservice.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public Optional<Category> getById(final int id) {
		return categoryRepository.findById(id);
	}
	
	public Category updateCategory(final int id, final Category category) {
		return categoryRepository.save(category);
	}
	
	public Category saveCategory(final Category category) {
		return categoryRepository.save(category);
	}
	
	public void deleteCategory(final int id) {
		categoryRepository.deleteById(id);
	}
	
	public Category findCategoryById(final int id) {
		return categoryRepository.findCategoryById(id);
	}
	
}

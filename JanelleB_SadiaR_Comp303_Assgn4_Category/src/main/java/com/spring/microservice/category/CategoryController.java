package com.spring.microservice.category;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

// request mapping so that for this service all url will start with category after localhost
@RequestMapping("category")
@Controller
public class CategoryController {

	// connection to the queries in the repositories through Service
	@Autowired
	private CategoryService categoryService;

	// to display the index page
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	// to display all categories
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("category", categoryService.getAll());
		return "categoryDisplay";
	}
	
	// method to add a category
	@PostMapping("/show")
	public String add(@Valid Category category, BindingResult result, Model model)
	{
		// if there is an error in the user input it will return the page with the validation message
		if (result.hasErrors()) {
			return "categoryAdd";
		}
		
		categoryService.saveCategory(category);
		model.addAttribute("category", categoryService.getAll());
		return "categoryDisplay";
	}
	
	// method to find a category by id
	@PostMapping("/findById")
	public @ResponseBody ModelAndView findById(@RequestParam("jobCatId") int jobCatId, Model model, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		
		// validation to check if the category is found to return the category
		if (categoryService.getById(jobCatId).isPresent())
		{
			Category category = categoryService.getById(jobCatId).get();
			List<Category> catList = new ArrayList<>();
			catList.add(category);
			mv.setViewName("categoryDisplay");
			mv.addObject("category", catList);
			return mv;
		}
		
		// if category is not found, it will redirect to category index page with the error message
		redirect.addFlashAttribute("message", "Category not found!");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	// delete the category by id
	@PostMapping("/delete/{jobCatId}")
	public String delete(@PathVariable("jobCatId") int jobCatId, Model model)
	{
		// validation to check if the category is found to return the category to be deleted
		if (categoryService.getById(jobCatId).isPresent())
		{
			categoryService.deleteCategory(jobCatId);
			model.addAttribute("category", categoryService.getAll());
			return "redirect:/category/show";
		}
		
		model.addAttribute("message", "Category not found!");
		return "index";
	}
	
	// update the category by id
	@PostMapping("/update/{jobCatId}")
	public String update(@PathVariable("jobCatId") int jobCatId, @Valid Category category, BindingResult result, Model model)
	{	
		// validation to check if the category is found to return the category to be updated
		if (categoryService.getById(jobCatId).isPresent()) {
			
			// if there is an error in the user input it will return the page with the validation message
			if (result.hasErrors()) {
				return "categoryUpdate";
			}
			
			categoryService.updateCategory(jobCatId, category);
			model.addAttribute("category", categoryService.getAll());
			return "redirect:/category/show";
		} 
		
		model.addAttribute("message", "Category not found!");
		return "index";
	}
	
	// method to get the update page with the existing details of the category
	@GetMapping("/update/{jobCatId}")
	public String renderUpdate(@PathVariable("jobCatId") int jobCatId, @ModelAttribute("category")Category category, Model model)
	{
		category = categoryService.findCategoryById(jobCatId);
		model.addAttribute("category", category);
		return "categoryUpdate"; 
	}
	
	// method to get to the add category form
	@GetMapping("/add")
	public String renderAdd(Category category) {
		return "categoryAdd";
	}
	
	// method to get to the find by id form
	@GetMapping("/findById")
	public String renderFindById()
	{
		return "categoryFindById";
	}
}

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

@RequestMapping("category")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("category", categoryService.getAll());
		return "categoryDisplay";
	}
	
	@PostMapping("/show")
	public String add(@Valid Category category, BindingResult result, Model model)
	{
		if (result.hasErrors()) {
			return "categoryAdd";
		}
		
		categoryService.saveCategory(category);
		model.addAttribute("category", categoryService.getAll());
		return "categoryDisplay";
	}
	
	@PostMapping("/findById")
	public @ResponseBody ModelAndView findById(@RequestParam("jobCatId") int jobCatId, Model model, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		
		if (categoryService.getById(jobCatId).isPresent())
		{
			Category category = categoryService.getById(jobCatId).get();
			List<Category> catList = new ArrayList<>();
			catList.add(category);
			mv.setViewName("categoryDisplay");
			mv.addObject("category", catList);
			return mv;
		}
		
		redirect.addFlashAttribute("message", "Category not found!");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/delete/{jobCatId}")
	public String delete(@PathVariable("jobCatId") int jobCatId, Model model)
	{
		if (categoryService.getById(jobCatId).isPresent())
		{
			categoryService.deleteCategory(jobCatId);
			model.addAttribute("category", categoryService.getAll());
			return "categoryDisplay";
		}
		
		model.addAttribute("message", "Category not found!");
		return "index";
	}
	
	@PostMapping("/update/{jobCatId}")
	public String update(@PathVariable("jobCatId") int jobCatId, @Valid Category category, BindingResult result, Model model)
	{	
		if (categoryService.getById(jobCatId).isPresent()) {
			
			if (result.hasErrors()) {
				return "categoryUpdate";
			}
			
			categoryService.updateCategory(jobCatId, category);
			model.addAttribute("category", categoryService.getAll());
			return "categoryDisplay";
		} 
		
		model.addAttribute("message", "Category not found!");
		return "index";
	}
	
	@GetMapping("/update/{jobCatId}")
	public String renderUpdate(@PathVariable("jobCatId") int jobCatId, @ModelAttribute("category")Category category, Model model)
	{
		category = categoryService.findCategoryById(jobCatId);
		model.addAttribute("category", category);
		return "categoryUpdate"; 
	}
	
	@GetMapping("/add")
	public String renderAdd(Category category) {
		return "categoryAdd";
	}
	
	@GetMapping("/findById")
	public String renderFindById()
	{
		return "categoryFindById";
	}
}

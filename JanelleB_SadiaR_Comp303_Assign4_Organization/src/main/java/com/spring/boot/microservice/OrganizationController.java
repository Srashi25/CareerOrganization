package com.spring.boot.microservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("organization")
@Controller
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	// Rendering Index page
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("organization", organizationService.getAll());
		return "orgDisplay";
	}
	
	// Getting all the organizations
	@PostMapping("/show")
	public String add(@Valid Organization organization, BindingResult result, Model model)
	{
		if (result.hasErrors()) {
			return "orgAdd";
		}
		
		organizationService.saveOrganization(organization);
		model.addAttribute("organization", organizationService.getAll());
		return "orgDisplay";
	}
	
	
	// Finding an organization by id
	@PostMapping("/findById")
	public @ResponseBody ModelAndView findById(@RequestParam("orgId") int orgId, Model model, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		
		if (organizationService.getOrgById(orgId).isPresent())
		{
			Organization organization = organizationService.getOrgById(orgId).get();
			List<Organization> orgList = new ArrayList<>();
			orgList.add(organization);
			mv.setViewName("orgDisplay");
			mv.addObject("organization", orgList);
			return mv;
		}
		
		redirect.addFlashAttribute("message", "Organziation not found!");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	// Handling Delete 
	@PostMapping("/delete/{orgId}")
	public String delete(@PathVariable("orgId") int orgId, Model model)
	{
		if (organizationService.getOrgById(orgId).isPresent())
		{
			organizationService.deleteOrganization(orgId);
			model.addAttribute("organization", organizationService.getAll());
			return "redirect:/organization/show";
		}
		
		model.addAttribute("message", "Organization not found!");
		return "index";
	}
	
	
	// Handling post update
	@PostMapping("/update/{orgId}")
	public String update(@PathVariable("orgId") int orgId, @Valid Organization organization, BindingResult result, Model model)
	{	
		if (organizationService.getOrgById(orgId).isPresent()) {
			
			if (result.hasErrors()) {
				return "orgUpdate";
			}
			
			organizationService.updateOrganization(orgId, organization);
			model.addAttribute("organization", organizationService.getAll());
			return "redirect:/organization/show";
		} 
		
		model.addAttribute("message", "Organization not found!");
		return "index";
	}
	
	
	//Rendering Update Page
	@GetMapping("/edit/{orgId}")
	public String renderUpdate(@PathVariable("orgId") int orgId, @ModelAttribute("organization")Organization organization, Model model)
	{
		organization = organizationService.findOneById(orgId);
		model.addAttribute("organization", organization);
		return "orgUpdate"; 
	}
	
	
	// Rendering add an organization page
	@GetMapping("/add")
	public String renderAdd(Organization organization) {
		return "orgAdd";
	}
	
	//Rendering find an organization page
	@GetMapping("/findById")
	public String renderFindById()
	{
		return "orgFindById";
	}
	
}
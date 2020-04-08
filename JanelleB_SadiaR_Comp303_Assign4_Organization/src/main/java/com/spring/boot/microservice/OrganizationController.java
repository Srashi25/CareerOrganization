package com.spring.boot.microservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
			return "orgDisplay";
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
			return "orgDisplay";
		} 
		
		model.addAttribute("message", "Organization not found!");
		return "index";
	}
	
	
	//Rendering Update Page
	@GetMapping("/update/{orgId}")
	public String renderUpdate(@PathVariable("orgId") int orgId, Organization organization, Model model)
	{
		model.addAttribute("orgId", orgId);
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
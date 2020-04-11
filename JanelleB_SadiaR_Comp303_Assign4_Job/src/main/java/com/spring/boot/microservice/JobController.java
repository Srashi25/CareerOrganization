package com.spring.boot.microservice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

//request mapping so that for this service all url will start with job after localhost
@RequestMapping("job")
@Controller
public class JobController {
	
	// connection to the queries in the repositories through Service
	@Autowired
	private JobService jobService;

	// Rendering Index page
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	// to display all jobs
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("job", jobService.getAll());
		return "jobDisplay";
	}
	
	// adding organization
	@PostMapping("/show")
	public String add(@Valid Job job, BindingResult result, Model model)
	{
		// if there is an error in the user input it will return the page with the validation message
		if (result.hasErrors()) {
			return "jobAdd";
		}
		
		jobService.saveJob(job);
		model.addAttribute("job", jobService.getAll());
		return "jobDisplay";
	}
	
	
	// Finding an organization by id
	@PostMapping("/findById")
	public @ResponseBody ModelAndView findById(@RequestParam("jobId") int jobId, Model model, RedirectAttributes redirect)
	{
		ModelAndView mv = new ModelAndView();
		
		// if there is an error in the user input it will return the page with the validation message
		if (jobService.getJobById(jobId).isPresent())
		{
			Job job = jobService.getJobById(jobId).get();
			List<Job> jobList = new ArrayList<>();
			jobList.add(job);
			mv.setViewName("jobDisplay");
			mv.addObject("job", jobList);
			return mv;
		}
		
		// if job is not found, it will redirect to category index page with the error message
		redirect.addFlashAttribute("message", "Job not found!");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	// Handling Delete 
	@PostMapping("/delete/{jobId}")
	public String delete(@PathVariable("jobId") int jobId, Model model)
	{
		// if there is an error in the user input it will return the page with the validation message
		if (jobService.getJobById(jobId).isPresent())
		{
			jobService.deleteJob(jobId);
			model.addAttribute("job", jobService.getAll());
			return "redirect:/job/show";
		}
		
		model.addAttribute("message", "Job not found!");
		return "index";
	}
	
	
	// Handling post update
	@PostMapping("/update/{jobId}")
	public String update(@PathVariable("jobId") int jobId, @Valid Job job, BindingResult result, Model model)
	{	
		// if there is an error in the user input it will return the page with the validation message
		if (jobService.getJobById(jobId).isPresent()) {
			
			// if there is an error in the user input it will return the page with the validation message
			if (result.hasErrors()) {
				return "jobUpdate";
			}
			
			jobService.updateJob(jobId, job);
			model.addAttribute("job", jobService.getAll());
			return "redirect:/job/show";
		} 
		
		model.addAttribute("message", "Job not found!");
		return "index";
	}
	
	
	//Rendering Update Page
	@GetMapping("/update/{jobId}")
	public String renderUpdate(@PathVariable("jobId") int jobId, @ModelAttribute("job")Job job, Model model)
	{
		job = jobService.findOneById(jobId);
		model.addAttribute("job", job);
		return "jobUpdate"; 
	}
	
	// Rendering add a job page
	@GetMapping("/add")
	public String renderAdd(Job job) {
		return "jobAdd";
	}
	
	//Rendering find a job page
	@GetMapping("/findById")
	public String renderFindById()
	{
		return "jobFindById";
	}
	
}
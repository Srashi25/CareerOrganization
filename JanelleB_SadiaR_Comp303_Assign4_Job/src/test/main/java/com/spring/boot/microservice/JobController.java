package com.spring.boot.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@RequestMapping("job")
@Controller
public class JobController {
	@Autowired
   private JobService jobService;

	// Rendering Index page
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("job", jobService.getAll());
		return "jobDisplay";
	}
	
	// Getting all the organizations
	@PostMapping("/show")
	public String add(@Valid Job job, BindingResult result, Model model)
	{
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
		
		if (jobService.getJobById(jobId).isPresent())
		{
			Job job = jobService.getJobById(jobId).get();
			List<Job> jobList = new ArrayList<>();
			jobList.add(job);
			mv.setViewName("jobDisplay");
			mv.addObject("job", jobList);
			return mv;
		}
		
		redirect.addFlashAttribute("message", "Job not found!");
		mv.setViewName("redirect:/");
		return mv;
	}
	
	// Handling Delete 
	@PostMapping("/delete/{jobId}")
	public String delete(@PathVariable("jobId") int jobId, Model model)
	{
		if (jobService.getJobById(jobId).isPresent())
		{
			jobService.deleteJob(jobId);
			model.addAttribute("job", jobService.getAll());
			return "jobDisplay";
		}
		
		model.addAttribute("message", "Job not found!");
		return "index";
	}
	
	
	// Handling post update
	@PostMapping("/update/{jobId}")
	public String update(@PathVariable("jobId") int jobId, @Valid Job job, BindingResult result, Model model)
	{	
		if (jobService.getJobById(jobId).isPresent()) {
			
			if (result.hasErrors()) {
				return "jobUpdate";
			}
			
			jobService.updateJob(jobId, job);
			model.addAttribute("job", jobService.getAll());
			return "jobDisplay";
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




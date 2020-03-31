package com.spring.boot.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class JobController {
//	@Autowired 
//	private JobRepository jobRepository;
//
//	@RequestMapping("/")
//	public String home()
//	{
//		return "index";
//	}
//	@PostMapping("/add")
//    public @ResponseBody String add(@RequestParam("jobId") int jobId,
//            @RequestParam("jobCode") String jobCode,
//            @RequestParam("jobName") String jobName,
//            @RequestParam("jobDesc") String jobDesc,
//            @RequestParam("pubDate") String pubDate,
//            @RequestParam("numVacancy") int numVacancy)
//    {
//		Job job=new Job(jobId,jobCode,jobName,jobDesc,pubDate,numVacancy);
//        jobRepository.save(job);
//        return "Job is Added";
//    }
//	@RequestMapping("/update")
//	public String update()
//	{
//		return "update";
//	}
//	
//	@PostMapping("/update")
//    public @ResponseBody String update(@RequestParam("jobId") int jobId,
//            @RequestParam("jobCode") String jobCode,
//            @RequestParam("jobName") String jobName,
//            @RequestParam("jobDesc") String jobDesc,
//            @RequestParam("pubDate") String pubDate,
//            @RequestParam("numVacancy") int numVacancy)
//    {
//		Job job=new Job(jobId,jobCode,jobName,jobDesc,pubDate,numVacancy);
//        jobRepository.save(job);
//        return "Job is Updated";
//    }
//	@RequestMapping("/show")
//    public String show(Model model)
//    {
//        model.addAttribute("jobList", jobRepository.findAll());
//        return "show";
//    }
//	@PostMapping("/delete")
//    public @ResponseBody String delete(@RequestParam("jobId") int jobId)
//    {
//        jobRepository.deleteById(jobId);
//        return "Job is Deleted";
//    }
//}


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class JobController {
	@Autowired
    JobRepository jobRepository;
	List<Job> findJobs = new ArrayList<Job>();
	
	// Rendering Index page

	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	//Rendering add_job page
		@RequestMapping(value="/add_job",method=RequestMethod.GET)
		public ModelAndView add_job()
		{
			ModelAndView view=new ModelAndView("add_job");
			return view;
		}
		//Rendering job_ info page
				@RequestMapping(value="/job_info",method=RequestMethod.GET)
				public ModelAndView job_info()
				{
					ModelAndView view=new ModelAndView("job_info");
					return view;
				}
				//Rendering contact page
						@RequestMapping(value="/contact",method=RequestMethod.GET)
						public ModelAndView contact()
						{
							ModelAndView view=new ModelAndView("contact");
							return view;
						}
	
	// accessing specific job 
 @RequestMapping(value = "/job/{jobId}", method = RequestMethod.GET)
 Job getJob(@PathVariable("jobId") int jobId) throws Exception {
     return jobRepository.getOne(jobId);
 }

 // Getting the list of jobs 
 @RequestMapping(value = "/jobs", method = RequestMethod.GET)
 Iterable<Job> getJobs() {
     return jobRepository.findAll();
 }
 // Rendering display page 
 @RequestMapping(value = "/display", method = RequestMethod.GET)
 public ModelAndView getJobList()
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("job_info");
     mv.addObject("jobList",jobRepository.findAll());
     return mv;
 }
 // Adding a job into jobService
 @RequestMapping(value = "/job", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
 @ResponseStatus(value = HttpStatus.OK)
 void addJob(@RequestBody Job job) throws Exception {
	 jobRepository.save(job);
     
 }

 // Saving the job consuming API
 @RequestMapping(value = "/display", method = RequestMethod.POST)
 public ModelAndView saveJob(@ModelAttribute Job job) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("job_info");
     jobRepository.save(job);
     mv.addObject("jobList",jobRepository.findAll());
     return mv;
 }
 //
 @RequestMapping(value="find_job", produces=MediaType.TEXT_PLAIN_VALUE)
 @ResponseBody
 public ModelAndView findJob(@RequestParam("jobTitle") String jobTitle) throws Exception {
	 findJobs.clear();
	 System.out.println("job Title"+jobTitle.toUpperCase());
	 ModelAndView mv = new ModelAndView();
     for(Job job:jobRepository.findAll()) {
    	 String name=job.getJobName().toUpperCase();
    	 System.out.println("job name"+name);
    	 if(name.equals(jobTitle.toUpperCase())) {
    		 findJobs.add(job);
    	 }
     }
     mv.setViewName("job_info");
     mv.addObject("jobList",findJobs);
     return mv;
 }

 // Getting the jobId and update the job object 
 @RequestMapping(value = "/job/{jobId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
 @ResponseStatus(value = HttpStatus.OK)
 void updateJob(@PathVariable("jobId") int jobId, @RequestBody Job job) throws Exception {
    job.setJobId(jobId);
    jobRepository.save(job);
 }

 // Rendering update_job page and displaying job object info on the page. 
 @RequestMapping(value = "update",params = "jobId", method = RequestMethod.GET)
 public ModelAndView getUpdateJob(@RequestParam("jobId") int jobId) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("update_job");
     mv.addObject("job",jobRepository.findById(jobId));
     return mv;
 }
 
 //Accepting the requested from and updating  the job and redirecting to jobList page
 @RequestMapping(value = "update", method = RequestMethod.POST)
 public ModelAndView updateJob(@ModelAttribute Job job) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("job_info");
     jobRepository.save(job);
     mv.addObject("jobList",jobRepository.findAll());
     return mv;
 }
 //getting jobId and Deleting object from jobRepo

 @RequestMapping(value = "/job/{jobId}", method = RequestMethod.DELETE)
 @ResponseStatus(value = HttpStatus.OK)
 void deleteJob(@PathVariable("jobId") int jobId) throws Exception {
	 jobRepository.deleteById(jobId);
 }
 
 //Receiving the request of deleting the object, receiving the Id of the object to delete and redirecting to jobList page
 @RequestMapping(value = "/delete/{jobId}", method = RequestMethod.GET)
 public ModelAndView getDeleteJob(@PathVariable("jobId") int jobId) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("job_info");
     jobRepository.deleteById(jobId);
     mv.addObject("jobList",jobRepository.findAll());
     return mv;
 }
}




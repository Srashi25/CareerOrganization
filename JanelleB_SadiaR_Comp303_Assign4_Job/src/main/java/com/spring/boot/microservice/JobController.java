package com.spring.boot.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobController {
	@Autowired 
	private JobRepository jobRepository;

	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	@PostMapping("/add")
    public @ResponseBody String add(@RequestParam("jobId") int jobId,
            @RequestParam("jobCode") String jobCode,
            @RequestParam("jobName") String jobName,
            @RequestParam("jobDesc") String jobDesc,
            @RequestParam("pubDate") String pubDate,
            @RequestParam("numVacancy") int numVacancy)
    {
		Job job=new Job(jobId,jobCode,jobName,jobDesc,pubDate,numVacancy);
        jobRepository.save(job);
        return "Job is Added";
    }
	
	@PostMapping("/update")
    public @ResponseBody String update(@RequestParam("jobId") int jobId,
            @RequestParam("jobCode") String jobCode,
            @RequestParam("jobName") String jobName,
            @RequestParam("jobDesc") String jobDesc,
            @RequestParam("pubDate") String pubDate,
            @RequestParam("numVacancy") int numVacancy)
    {
		Job job=new Job(jobId,jobCode,jobName,jobDesc,pubDate,numVacancy);
        jobRepository.save(job);
        return "Job is Updated";
    }
	@RequestMapping("/show")
    public String show(Model model)
    {
        model.addAttribute("job", jobRepository.findAll());
        return "show";
    }
	@PostMapping("/delete")
    public @ResponseBody String delete(@RequestParam("jobId") int jobId)
    {
        jobRepository.deleteById(jobId);
        return "Job is Deleted";
    }
}



package com.spring.boot.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;
	
	public List<Job> getAll() {
		return jobRepository.findAll();
	}
	
	public Optional<Job> getJobById(final int id) {
		return jobRepository.findById(id);
	}
	
	public Job updateJob(final int id, final Job job) {
		return jobRepository.save(job);
	}
	
	public Job saveJob(final Job job) {
		return jobRepository.save(job);
	}
	
	public void deleteJob(final int id) {
		jobRepository.deleteById(id);
	}


}

package com.spring.boot.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

//job service created to connect to the repository and to be able to use special methods to query
@Service
public class JobService {
	
	// connecting to the repository
	@Autowired
	private JobRepository jobRepository;
	
	// select all job
	public List<Job> getAll() {
		return jobRepository.findAll();
	}
	
	// optional is used to be able to use the isPresent() method
	public Optional<Job> getJobById(final int id) {
		return jobRepository.findById(id);
	}
	
	// updating the job with the job id and saving it
	public Job updateJob(final int id, final Job job) {
		return jobRepository.save(job);
	}
	
	// saving the job
	public Job saveJob(final Job job) {
		return jobRepository.save(job);
	}
	
	// deleting the job
	public void deleteJob(final int id) {
		jobRepository.deleteById(id);
	}
	
	// finding the job by id - used for the update
	public Job findOneById(final int id) {
		return jobRepository.findOneById(id);
	}
	

}

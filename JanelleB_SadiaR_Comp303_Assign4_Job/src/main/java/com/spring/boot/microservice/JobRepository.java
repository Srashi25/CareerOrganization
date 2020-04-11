package com.spring.boot.microservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

public interface JobRepository extends JpaRepository<Job, Integer> {

	@Query("SELECT j FROM Job j WHERE j.jobId = ?1")
	Job findOneById(int jobid);
	
}
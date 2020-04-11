package com.spring.boot.microservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Integer> {

	@Query("SELECT j FROM Job j WHERE j.jobId = ?1")
	Job findOneById(int jobid);
	
}
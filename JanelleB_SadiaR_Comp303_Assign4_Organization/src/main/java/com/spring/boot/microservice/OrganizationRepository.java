package com.spring.boot.microservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	// this query is used for the update so that it will return the instance of the org
	@Query("SELECT o FROM Organization o WHERE o.orgId = ?1")
	Organization findOneById(int orgid);
	
}

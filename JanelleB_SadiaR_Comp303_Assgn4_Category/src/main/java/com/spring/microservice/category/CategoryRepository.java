package com.spring.microservice.category;


import org.springframework.data.jpa.repository.*;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	// this query is used for the update so that it will return the instance of the category
	@Query("SELECT c FROM Category c WHERE c.jobCatId = ?1")
	Category findCategoryById(int jobcatid);
}

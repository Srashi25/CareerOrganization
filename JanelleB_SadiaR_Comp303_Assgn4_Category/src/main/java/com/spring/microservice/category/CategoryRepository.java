package com.spring.microservice.category;


import org.springframework.data.jpa.repository.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c WHERE c.jobCatId = ?1")
	Category findCategoryById(int jobcatid);
}

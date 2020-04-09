package com.spring.boot.microservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	@Query("SELECT o FROM Organization o WHERE o.orgId = ?1")
	Organization findOneById(int orgid);
}

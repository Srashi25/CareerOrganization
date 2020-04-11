package com.spring.boot.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

//category service created to connect to the repository and to be able to use special methods to query
@Service
public class OrganizationService {
	
	// connecting to the repository
	@Autowired
	private OrganizationRepository organizationRepository;
	
	// select all org
	public List<Organization> getAll() {
		return organizationRepository.findAll();
	}
	
	// optional is used to be able to use the isPresent() method
	public Optional<Organization> getOrgById(final int id) {
		return organizationRepository.findById(id);
	}
	
	// updating the org with the org id and saving it
	public Organization updateOrganization(final int id, final Organization organization) {
		return organizationRepository.save(organization);
	}
	
	// saving the org
	public Organization saveOrganization(final Organization organization) {
		return organizationRepository.save(organization);
	}
	
	// deleting the org
	public void deleteOrganization(final int id) {
		organizationRepository.deleteById(id);
	}

	// finding the org by id - used for the update
	public Organization findOneById(final int id) {
		return organizationRepository.findOneById(id);
	}
}

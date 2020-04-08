package com.spring.boot.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	public List<Organization> getAll() {
		return organizationRepository.findAll();
	}
	
	public Optional<Organization> getOrgById(final int id) {
		return organizationRepository.findById(id);
	}
	
	public Organization updateOrganization(final int id, final Organization organization) {
		return organizationRepository.save(organization);
	}
	
	public Organization saveOrganization(final Organization organization) {
		return organizationRepository.save(organization);
	}
	
	public void deleteOrganization(final int id) {
		organizationRepository.deleteById(id);
	}

}

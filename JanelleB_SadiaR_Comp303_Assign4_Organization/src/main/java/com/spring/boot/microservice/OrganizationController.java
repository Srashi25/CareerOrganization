package com.spring.boot.microservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OrganizationController {
	@Autowired
    OrganizationRepository organizationRepository;
	List<Organization> findOrganization = new ArrayList<Organization>();
	
	// Rendering Index page

	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	//Rendering add_job page
		@RequestMapping(value="/add_org",method=RequestMethod.GET)
		public ModelAndView add_org()
		{
			ModelAndView view=new ModelAndView("add_org");
			return view;
		}
		//Rendering job_ info page
				@RequestMapping(value="/org_info",method=RequestMethod.GET)
				public ModelAndView job_info()
				{
					ModelAndView view=new ModelAndView("org_info");
					return view;
				}
				//Rendering contact page
						@RequestMapping(value="/contact",method=RequestMethod.GET)
						public ModelAndView contact()
						{
							ModelAndView view=new ModelAndView("contact");
							return view;
						}
	
	// accessing specific job 
 @RequestMapping(value = "/org/{orgId}", method = RequestMethod.GET)
 Organization getOrganization(@PathVariable("orgId") int orgId) throws Exception {
     return organizationRepository.getOne(orgId);
 }

 // Getting the list of organizations
 @RequestMapping(value = "/organizations", method = RequestMethod.GET)
 Iterable<Organization> getOrganizations() {
     return organizationRepository.findAll();
 }
 // Rendering display page 
 @RequestMapping(value = "/display", method = RequestMethod.GET)
 public ModelAndView getOrganizationList()
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("org_info");
     mv.addObject("orgList",organizationRepository.findAll());
     return mv;
 }
 // Adding a organization into OrgRepo
 @RequestMapping(value = "/org", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
 @ResponseStatus(value = HttpStatus.OK)
 void addOrganization(@RequestBody Organization organization) throws Exception {
	 organizationRepository.save(organization);
     
 }

 // Saving the organization consuming API
 @RequestMapping(value = "/display", method = RequestMethod.POST)
 public ModelAndView saveJob(@ModelAttribute Organization organization) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("org_info");
     organizationRepository.save(organization);
     mv.addObject("orgList",organizationRepository.findAll());
     return mv;
 }
 //Finding an Organization with Organization Name
 @RequestMapping(value="find_org", produces=MediaType.TEXT_PLAIN_VALUE)
 @ResponseBody
 public ModelAndView findJob(@RequestParam("orgName") String orgName) throws Exception {
	 findOrganization.clear();
	 System.out.println("Organization name "+orgName.toUpperCase());
	 ModelAndView mv = new ModelAndView();
     for(Organization organization:organizationRepository.findAll()) {
    	 String name=organization.getOrgName().toUpperCase();
    	 System.out.println("Org name"+name);
    	 if(name.equals(orgName.toUpperCase())) {
    		 findOrganization.add(organization);
    	 }
     }
     mv.setViewName("org_info");
     mv.addObject("orgList",findOrganization);
     return mv;
 }

 // Getting the orgId and update the organization object 
 @RequestMapping(value = "/organization/{orgId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
 @ResponseStatus(value = HttpStatus.OK)
 void updateOrganization(@PathVariable("orgId") int orgId, @RequestBody Organization organization) throws Exception {
    organization.setOrgId(orgId);
    organizationRepository.save(organization);
 }

 // Rendering update_org page and displaying org object info on the page. 
 @RequestMapping(value = "update",params = "orgId", method = RequestMethod.GET)
 public ModelAndView getUpdateOrganization(@RequestParam("orgId") int orgId) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("update_org");
     mv.addObject("organization",organizationRepository.findById(orgId));
     return mv;
 }
 
 //Accepting the requested from and updating  the organization and redirecting to orgList page
 @RequestMapping(value = "update", method = RequestMethod.POST)
 public ModelAndView updateJob(@ModelAttribute Organization organization) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("org_info");
     organizationRepository.save(organization);
     mv.addObject("orgList",organizationRepository.findAll());
     return mv;
 }
 //getting orgId and Deleting object from orgRepo
//
 @RequestMapping(value = "/organization/{orgId}", method = RequestMethod.DELETE)
 @ResponseStatus(value = HttpStatus.OK)
 void deleteOrganization(@PathVariable("orgId") int orgId) throws Exception {
	 organizationRepository.deleteById(orgId);
 }
 
 //Receiving the request of deleting the object, receiving the Id of the object to delete and redirecting to jobList page
 @RequestMapping(value = "/delete/{orgId}", method = RequestMethod.GET)
 public ModelAndView getDeleteJob(@PathVariable("orgId") int orgId) throws Exception
 {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("org_info");
     organizationRepository.deleteById(orgId);
     mv.addObject("orgList",organizationRepository.findAll());
     return mv;
 }
}
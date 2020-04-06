package com.spring.boot.microservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="organization")
public class Organization {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="orgid")
	private int orgId;
	
	@NotEmpty (message = "Organization Name is")
	@Column(name="orgname")
	private String orgName;
	
	@Length (min=20,max=100,message = "Address cannot be null.")
	@Column(name="address")
	private String address;
	
	@Pattern( regexp="^\\(?(\\ d{3}\\)?[- ]?(\\d{3})[- ]?(\\d{4 })$", message ="Phone number is invalid")
	@Column(name="postalcode")
	private String postalCode;
	
	@Column(name="phoneno")
	private String phoneNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="website")
	private String website;
	
	
	public Organization() {
		super();
	}
	public Organization(int orgId, String orgName, String address, String postalCode, String phoneNo, String email,
			String website) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNo = phoneNo;
		this.email = email;
		this.website = website;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}


}


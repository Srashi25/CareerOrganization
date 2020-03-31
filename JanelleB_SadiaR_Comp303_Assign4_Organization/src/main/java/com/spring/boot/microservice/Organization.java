package com.spring.boot.microservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="organization")
public class Organization {
	
	@Id
	@Column(name="orgid")
	private int orgId;
	
	@Column(name="orgname")
	private String orgName;
	
	@Column(name="address")
	private String address;
	
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


package com.spring.boot.microservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

//Entity and Table for Org
@Entity
@Table(name="organization")
public class Organization {
	
	// Properties of Org
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO) // Primary key auto-generated
	@Column(name="orgid")
	private int orgId;
	
	@NotNull(message="Please add organization name.") // validation
	@NotEmpty (message = "Please add organization name.") // validation
	@Column(name="orgname")
	private String orgName;
	
	@NotNull(message="Please add address.") // validation
	@Length (min=10,max=100,message = "Address should be between 10-100 characters.") // validation
	@Column(name="address")
	private String address;
	
	@NotNull(message="Please add postal code.") // validation
	@Pattern(regexp="^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]●?[0-9][A-Z][0-9]$", message ="Postal code is invalid.") // validation
	@Column(name="postalcode")
	private String postalCode;
	
	@NotNull(message="Please add phone number.") // validation
	@Pattern(regexp="^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$", message="Phone number is invalid") // validation
	@Column(name="phoneno")
	private String phoneNo;
	
	@NotNull(message="Please add email.") // validation
	@Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
			message="{invalid.email}") // validation
	@Column(name="email")
	private String email;
	
	@URL // validation
	@Column(name="website")
	private String website;
	
	// constructors
	
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
	
	// getters and setters
	
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


package com.spring.microservice.category;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */

// Entity and Table for Category
@Entity
@Table(name="category")
public class Category {
	
	// Properties of Category
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Primary key auto-generated
	@Column(name="jobcatid")
	private int jobCatId;
	
	@NotNull // validation
	@Size(min=2, max=10) // validation
	@NotEmpty(message = "Please enter category code.")  // validation
	@Column(name="catcode")
	private String catCode;
	
	@NotNull(message = "Please enter category name.") // validation
	@NotEmpty(message = "Please enter category name.") // validation
	@Column(name="catname")
	private String catName;
	
	@NotNull(message = "Please enter category description.") // validation
	@NotEmpty(message = "Please enter category description.") // validation
	@Column(name="catdesc")
	private String catDesc;
	
	// constructors
	public Category() {
		super();
	}

	public Category(int jobCatId, String catCode, String catName, String catDesc) {
		super();
		this.jobCatId = jobCatId;
		this.catCode = catCode;
		this.catName = catName;
		this.catDesc = catDesc;
	}
	
	// getters and setters

	public int getJobCatId() {
		return jobCatId;
	}

	public void setJobCatId(int jobCatId) {
		this.jobCatId = jobCatId;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

}

package com.spring.microservice.category;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="jobcatid")
	private int jobCatId;
	
	@NotNull
	@Size(min=2, max=10)
	@NotEmpty(message = "Please enter category code.")
	@Column(name="catcode")
	private String catCode;
	
	@NotNull(message = "Please enter category name.")
	@NotEmpty(message = "Please enter category name.")
	@Column(name="catname")
	private String catName;
	
	@NotNull(message = "Please enter category description.")
	@NotEmpty(message = "Please enter category description.")
	@Column(name="catdesc")
	private String catDesc;
	
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

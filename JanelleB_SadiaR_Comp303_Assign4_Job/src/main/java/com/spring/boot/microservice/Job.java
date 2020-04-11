package com.spring.boot.microservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Janelle Baetiong (300966120) and Sadia Rashid (300963357)
 * COMP303 - 001 - Lab Assignment#4
 */
//Entity and Table for Job
@Entity
@Table(name="job")
public class Job {
	
	// Properties of Job
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)// Primary key auto-generated
	@Column(name="jobid")
	private int jobId;
	
	@NotNull (message = "Job code is required.") // validation
	@Column(name="jobcode")
	private String jobCode;
	
	@NotNull (message = "Job name is required.") // validation
	@Column(name="jobname")
	private String jobName;
	
	@NotNull (message = "Job description is required.") // validation
	@Length (min=10,max=200,message = "Job description should be between 10-200 characters.") // validation
	@Column(name="jobdesc")
	private String jobDesc;
	
	@NotNull (message = "Pulished date is required.") // validation
	@Column(name="pubdate")
	private String pubDate;
	
	@NotNull (message = "Number of vacancies is required.") // validation
	@Column(name="numvacancy")
	@Digits(integer = 5, fraction = 0, message="Number without fraction required.") // validation
	private int numVacancy;
	
	// constructors
	public Job() {
		super();
	}
	
	public Job(int jobId, String jobCode, String jobName, String jobDesc, String pubDate, int numVacancy)  {
		super();
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobDesc = jobDesc;
		this.pubDate = pubDate;
		this.numVacancy = numVacancy;
	}
	
	// getters and setters
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate)  {
		this.pubDate = pubDate;
	}
	public int getNumVacancy() {
		return numVacancy;
	}
	public void setNumVacancy(int numVacancy) {
		this.numVacancy = numVacancy;
	}
}

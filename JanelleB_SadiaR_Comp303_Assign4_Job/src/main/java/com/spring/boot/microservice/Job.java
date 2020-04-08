package com.spring.boot.microservice;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="job")
public class Job {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="jobid")
	private int jobId;
	
	
	@NotNull (message = "Job code is required.")
	@Column(name="jobcode")
	private String jobCode;
	
	@NotNull (message = "Job name is required.")
	@Column(name="jobname")
	private String jobName;
	
	@NotNull (message = "Job description is required.")
	@Length (min=10,max=2000,message = "Job description should be between 10-200 characters.")
	@Column(name="jobdesc")
	private String jobDesc;
	
	@NotNull (message = "Pulished date is required.")
	@Column(name="pubdate")
	private String pubDate;
	
	@NotNull (message = "Number of vacancies is required.")
	@Column(name="numvacancy")
	private int numVacancy;
	
	public Job() {
		super();
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Job(int jobId, String jobCode, String jobName, String jobDesc, String pubDate, int numVacancy) {
		super();
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobDesc = jobDesc;
		this.pubDate = pubDate;
		this.numVacancy = numVacancy;
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
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public int getNumVacancy() {
		return numVacancy;
	}
	public void setNumVacancy(int numVacancy) {
		this.numVacancy = numVacancy;
	}
	public String getDate(String pubDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("ca", "CA"));
		String date = simpleDateFormat.format(new Date());
		return date.toString();
	}
	
}


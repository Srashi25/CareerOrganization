package com.spring.boot.microservice;

import java.text.ParseException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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
	@Length (min=10,max=200,message = "Job description should be between 10-200 characters.")
	@Column(name="jobdesc")
	private String jobDesc;
	
	@NotNull (message = "Pulished date is required.")
	@Column(name="pubdate")
	private String pubDate;
	
	@NotNull (message = "Number of vacancies is required.")
	@Column(name="numvacancy")
	@Digits(integer = 5, fraction = 0, message="Number without fraciton required.")
	private int numVacancy;
	
	//private String newDate;
	
	public Job() {
		super();
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Job(int jobId, String jobCode, String jobName, String jobDesc, String pubDate, int numVacancy) throws ParseException {
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
	public void setPubDate(String pubDate) throws ParseException {
//		 SimpleDateFormat formatter1 = new SimpleDateFormat("dd-M-yyyy", Locale.CANADA);
//		 formatter1.setTimeZone(TimeZone.getTimeZone("Canada/Toronto"));
//		this.pubDate = formatter1.parse(pubDate);
		this.pubDate = pubDate;
	}
	public int getNumVacancy() {
		return numVacancy;
	}
	public void setNumVacancy(int numVacancy) {
		this.numVacancy = numVacancy;
	}

	
}


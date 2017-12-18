package org.Actions;

import java.util.ArrayList;

import org.BusinessService.WebScrapingService;
import org.Interfaces.WebScrapingServiceI;
import org.Models.Job;

public class ScrapingAction {

	private ArrayList<Job> jobList;
	
	public String execute() {
		jobList = new ArrayList<Job>();
		
		WebScrapingServiceI scrapperService = new WebScrapingService();
		jobList = scrapperService.doScrapWeb();
		return "success";

	}
	
	public ArrayList<Job> getJobList() {
		return jobList;
	}
	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}
	
	
}

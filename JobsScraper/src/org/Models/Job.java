package org.Models;

public class Job {

	private String name;
	private String summary;
	private String location;
	private String company;
	private String pageLink;
	private String webSite;
	
	public Job() {
		
	}
	
	public Job(String namee, String locationn,String companyy,String pageLinkk) {
		name = namee;
		location = locationn;
		company = companyy;
		pageLink = pageLinkk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPageLink() {
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}

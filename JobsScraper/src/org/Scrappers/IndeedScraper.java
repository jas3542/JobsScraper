package org.Scrappers;

import java.io.IOException;
import java.util.ArrayList;

import org.Constants.WebUrlConstants;
import org.Models.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IndeedScraper {

	ArrayList<Job> listJob = new ArrayList<Job>();

	public IndeedScraper() {
	}

	public ArrayList<Job> fetchData(String url) {
		
		Job job = new Job();
		Object[] jobLinksUrl;
		String jobName = "";
		String jobCompany = "";
		String jobLocation = "";
		String jobLink = "";
		String jobSummary = "";
		int sizeJobs = 0;
		int sizeCompany = 0;
		int sizeLocation = 0;
		int sizeSummary = 0;
		boolean doScrap = false;

		if (getStatusCode(url) == 200) {
			Document document = getHtmlDocument(url);
			
			jobLinksUrl = document.select("[data-tn-element='jobTitle']").eachAttr("href").toArray();
			
			sizeJobs = document.select("[data-tn-element='jobTitle']").eachText().size();
			sizeCompany = document.select("span.company").eachText().size();
			sizeLocation = document.select("span.location").eachText().size();
			sizeSummary = document.select("span.summary").eachText().size();
			
			if (sizeJobs == sizeCompany && sizeJobs == sizeLocation && sizeJobs == sizeSummary &&
					sizeCompany == sizeLocation && sizeCompany == sizeSummary &&
					sizeLocation == sizeSummary) {
				doScrap = true;
			}
			
			if (doScrap) {
				for (int i = 0; i < sizeJobs; i++) {
					job = new Job();

					jobName = document.select("[data-tn-element='jobTitle']").eachText().get(i);
					jobSummary = document.select("span.summary").eachText().get(i);
					jobCompany = document.select("span.company").eachText().get(i);
					jobLocation = document.select("span.location").eachText().get(i);
					jobLink = WebUrlConstants.Indeed_URL_IE.concat((String) jobLinksUrl[i]);
					
					
					job.setWebSite(WebUrlConstants.Indeed);
					job.setName(jobName);
					job.setSummary(jobSummary);
					job.setCompany(jobCompany);
					job.setLocation(jobLocation);
					job.setPageLink(jobLink);

					listJob.add(job);
				}
			}
		}

		return listJob;

	}

	private Document getHtmlDocument(String url) {
		Document doc = new Document("empty");
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).get();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return doc;
	}

	private int getStatusCode(String url) {
		int responseCode = 0;
		try {
			responseCode = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).ignoreHttpErrors(true).execute().statusCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseCode;
	}

}

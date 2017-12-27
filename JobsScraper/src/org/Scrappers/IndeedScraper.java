package org.Scrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.Constants.WebUrlConstants;
import org.Models.Job;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IndeedScraper {

	ArrayList<Job> listJob = new ArrayList<Job>();

	public IndeedScraper() {
	}
	// TODO Erase Throw.
	public ArrayList<Job> fetchData(String url) throws IOException {
		
		Job job = new Job();
		Object[] jobLinksUrl;
		
		String jobName = "";
		String jobCompany = "";
		String jobLocation = "";
		String jobLink = "";
		String jobSummary = "";
		
		int sizeJobs = 0;
		int sizeSummary = 0;
		int sizeCompany = 0;
		int sizeLocation = 0;
		int sizeJobsUrlLink = 0;
		
		if (getStatusCode(url) == 200) {
			Document document = getHtmlDocument(url);
			// TODO Erase:
			/* creates a file with html in EclipseOxigen/
			File file = new File("html"+url.charAt(29)+url.charAt(30)+".html");
			FileUtils.writeStringToFile(file, document.outerHtml());
			*/
			
			jobLinksUrl = document.select("[data-tn-element='jobTitle']").eachAttr("href").toArray();
			sizeJobs = document.select("[data-tn-element='jobTitle']").eachText().size();
			sizeSummary = document.select("span.summary").eachText().size();
			sizeCompany = document.select("span.company").eachText().size();
			sizeLocation = document.select("span.location").eachText().size();
			sizeJobsUrlLink = jobLinksUrl.length-1;
			
			if (sizeJobs > 0) {
				for (int i = 0; i < sizeJobs; i++) {
					job = new Job();
					
					if (i >= sizeSummary) {
						jobSummary = "";
					}else if(i >= sizeCompany) {
						jobCompany = "";
					}else if(i >= sizeLocation) {
						jobLocation = "";
					}else if ( i >= sizeJobsUrlLink) {
						jobLink = "";
					}else {
						jobSummary = document.select("span.summary").eachText().get(i);
						jobCompany = document.select("span.company").eachText().get(i);
						jobLocation = document.select("span.location").eachText().get(i);
						jobLink = WebUrlConstants.Indeed_URL_IE.concat((String) jobLinksUrl[i]);
					}
					jobName = document.select("[data-tn-element='jobTitle']").eachText().get(i);
					
					
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

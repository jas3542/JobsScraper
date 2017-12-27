package org.Scrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.Constants.WebUrlConstants;
import org.Models.Job;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MonsterScrapper {
	ArrayList<Job> listJob = new ArrayList<Job>();

	public ArrayList<Job> fetchData(String url) throws IOException {

		Job job = new Job();
		String jobName = "";
		String jobCompany = "";
		String jobLocation = "";
		String jobLink = "";
		int sizeJobs = 0;
		int sizeCompany = 0;
		int sizeLocation = 0;
		int sizeLinkJob = 0;
		boolean doScrap = false;

		if (getStatusCode(url) == 200) {
			Document document = getHtmlDocument(url);

			// TODO Erase:
			/* creates a file with html in EclipseOxigen/
			File file = new File("html"+url.charAt(38)+".html");
			FileUtils.writeStringToFile(file, document.outerHtml());
			*/
			
			sizeJobs = document.select("div.jobTitle a span").eachText().size();
			sizeCompany = document.select("div.company a").eachAttr("title").size();
			sizeLocation = document.select("div.job-specs.job-specs-location p").eachText().size();
			sizeLinkJob = document.select("div.jobTitle a").eachAttr("href").size();
			
			if (sizeJobs == sizeCompany && sizeJobs == sizeLocation && sizeJobs == sizeLinkJob &&
					sizeCompany == sizeLocation && sizeCompany == sizeLinkJob &&
						sizeLocation == sizeLinkJob) {
				doScrap = true;
			}

			if (doScrap) {
				for (int i = 0; i < sizeJobs; i++) {
					job = new Job();

					jobName = document.select("div.jobTitle a span").eachText().get(i);
					jobCompany = document.select("div.company a").eachAttr("title").get(i);
					jobLocation = document.select("div.job-specs.job-specs-location p").eachText().get(i);
					jobLink = document.select("div.jobTitle a").eachAttr("href").get(i);

					job.setWebSite(WebUrlConstants.Monster);
					job.setName(jobName);
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

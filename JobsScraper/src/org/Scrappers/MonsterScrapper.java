package org.Scrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.Constants.WebUrlConstants;
import org.Models.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MonsterScrapper {
	ArrayList<Job> listJob = new ArrayList<Job>();

	public ArrayList<Job> fetchData(String url) {

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

			sizeJobs = document.select("div.jobTitle a span").eachText().size();
			sizeCompany = document.select("div.company a").eachAttr("title").size();
			sizeLocation = document.select("div.job-specs.job-specs-location p a").eachAttr("title").size();
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
					jobLocation = document.select("div.job-specs.job-specs-location p a").eachAttr("title").get(i);
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

		// TODO malament. Suma tot de indeed inclos estan el scrapper de Monster.
		return listJob;
	}

	private Document getHtmlDocument(String url) {
		Document doc = new Document("empty");
		try {
			System.out.println(new Date() + " <-Temps ARA");
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).get();
			System.out.println(new Date() + " <-Temps DSP");
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

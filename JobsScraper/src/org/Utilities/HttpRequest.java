package org.Utilities;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.Models.Job;
import org.Scrappers.IndeedScraper;
import org.Scrappers.MonsterScrapper;

/**
 * To make calls and get jobs.
 * 
 * 200 OK 300 Multiple Choices 301 Moved Permanently 305 Use Proxy 400 Bad
 * Request 403 Forbidden 404 Not Found 500 Internal Server Error 502 Bad Gateway
 * 503 Service Unavailable
 * 
 * using Jsoup tool
 * 
 * @author Jas
 *
 */
public class HttpRequest implements Callable {

	private String url = "";
	ArrayList<Job> listJob = new ArrayList<Job>();
	private String webSite;

	public HttpRequest(String webSitee, String urll) {
		this.webSite = webSitee;
		this.url = urll;
	}

	@Override
	public ArrayList<Job> call() throws Exception {

		if (this.webSite.contains("Indeed")) {
			IndeedScraper indeedScrap = new IndeedScraper();
			listJob.addAll(indeedScrap.fetchData(url));
		} else if (this.webSite.contains("Monster")) {
			MonsterScrapper monsterScrap = new MonsterScrapper();
			listJob.addAll(monsterScrap.fetchData(url));
		}

		return listJob;
	}
}

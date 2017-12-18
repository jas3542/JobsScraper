package org.BusinessService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.Constants.WebUrlConstants;
import org.Interfaces.WebScrapingServiceI;
import org.Models.Job;
import org.Utilities.HttpRequest;
import org.Utilities.Utility;

public class WebScrapingService implements WebScrapingServiceI {

	@Override
	public ArrayList<Job> doScrapWeb() {

		ArrayList<Job> jobResultList = new ArrayList<Job>();
		// URL for different websites
		TreeMap<String, String> urlsMap = new TreeMap<>();
		
		Utility u = new Utility();
		
		HttpRequest httpIndeed;
		HttpRequest httpMonster;
		

		urlsMap = u.readURLFromFile();

		// For loop on the keys
		Set<String> urlSetKeys = urlsMap.keySet();
		Iterator<String> it = urlSetKeys.iterator();
		
		while (it.hasNext()) {
			String key = it.next().toString();
			String value = urlsMap.get(it.next());
			// TODO: delete after finishing.
			//System.out.println("WebScrapingService-> "+ key+ " <> " + value);
			if (key.contains(WebUrlConstants.Indeed)) {
				try {
					httpIndeed = new HttpRequest(key,value);
					// Callable
					ExecutorService executor = Executors.newCachedThreadPool();
					Future<ArrayList<Job>> futureCall = executor.submit(httpIndeed);
					// Here the thread will be blocked until the result came back.
					jobResultList.addAll(futureCall.get());
				
					System.out.println("Indeed -> "+key);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}else if (key.contains(WebUrlConstants.Monster)) {
				try {
					httpMonster = new HttpRequest(key,value);
					// Callable
					ExecutorService executor = Executors.newCachedThreadPool();
					Future<ArrayList<Job>> futureCall = executor.submit(httpMonster);
					// Here the thread will be blocked until the result came back.
					jobResultList.addAll(futureCall.get());
					System.out.println("Monster -> "+key);
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}

		return jobResultList;
	}

}

package org.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Utility {

	public TreeMap<String, String> readURLFromFile() {
		TreeMap<String, String> urllist = new TreeMap<String, String>();
		InputStream inputStream = null;
		String propFileName = "urls.properties";
		String key="";
		String value="";
		try {
			Properties prop = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
				Set<String> setKeys = new TreeSet<String>(prop.stringPropertyNames());
				Iterator<String> it = setKeys.iterator();
				while (it.hasNext()) {
					key = it.next().toString();
					value = prop.getProperty(key,"");
					
					urllist.put(key,value );
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return urllist;
	}

}

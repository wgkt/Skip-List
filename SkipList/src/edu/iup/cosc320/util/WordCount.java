package edu.iup.cosc320.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		Map<String, Integer> words = new java.util.concurrent.ConcurrentSkipListMap<String, Integer>();
		Map<String, Integer> words = new SkipListMap<String, Integer>();

		Scanner s = new Scanner(new File("Sylabus.txt"));
		
		s.useDelimiter("[ ’\":~/,–\\-\\(\\)%\\t\\n.;]+");
		
		while (s.hasNext()) {
			String word = s.next().trim();
			
			if (word.length() == 0) {
				continue;
			}
			
			Integer count = words.get(word);
			
			if (count == null) {
				words.put(word, 1);
			} else {
				words.put(word, count + 1);
			}
		}
		
		for (String key : words.keySet()) {
			int count = words.get(key);
			System.out.println(key + " " + count);
		}

	}

}

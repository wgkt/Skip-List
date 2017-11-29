package edu.iup.cosc320.util;

import java.util.HashMap;
import java.util.Map;

public class TestSkipList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, String> map = new SkipListMap<Integer, String>();
		
//		((SkipListMap) map).setHeights(new int[] { 1, 3, 2, 1, 4, 4, 1, 1 }) ;
		
		map.put(2, "two");
		map.put(5, "five");
		map.put(10, "10");
		map.put(4, "four");
		map.put(3, "three");
		map.put(20, "twenty");
		map.put(22, "twentytwo");
		map.put(25, "twentyfive");
		
		System.out.println(map.get(20));
		
		map.remove(20);
		
		for (int key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		
		

	}

}

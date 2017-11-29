package edu.iup.cosc320.util;

import java.util.Random;

public class RandomHeight {
	private Random rand = new Random();
	private int maxHeight;
	private int[] initHeights;
	private int initHeightsPos;
	private static double LOG2 = Math.log(2.0);
	
	public RandomHeight(int maxHeight) {
		super();
		this.maxHeight = maxHeight;
	}
	
	public void setHeights(int[] initHeights) {
		this.initHeights = initHeights;
	}
	
	public int nextRandomHeight() {
		if (initHeights != null && initHeightsPos < initHeights.length) {
			return initHeights[initHeightsPos++];
		}
		
		int r;
		int rh = 1;
		
		while ((r = rand.nextInt(2)) == 1) {
			rh++;
			if (rh == maxHeight) {
				return rh - 1;
			}
		}
		return rh;
	}
	
	public static void main(String[] args) {
		int h = 20;
		RandomHeight rh = new RandomHeight(h);
		int[] counts = new int[h];
		
		for (int i = 0; i < 1000000; i++) {
			int j = rh.nextRandomHeight();
			counts[j - 1]++;
		}
		
		for (int i = 0; i < h; i ++) {
			System.out.printf("%d  %d\n", i, counts[i]);
		}
	}
}

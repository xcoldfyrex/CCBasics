package com.cfdigital.cc.ccbasics.misc;

public class TimeUtils {

	public static String getTimeLeft(long checkTime, int maxSeconds) {
		long CurrentTime = System.currentTimeMillis() / 1000L;
		long secondsLeft = (CurrentTime - (checkTime + maxSeconds)) * -1;
		int left = 0;
		int ss = 0;
		int mm = 0;
		int hh = 0;
		int dd = 0;
		left = (int) (secondsLeft);
		ss = left % 60;
		left = (int) left / 60;
		if (left > 0) {
			mm = left % 60;
			left = (int) left / 60;
			if (left > 0) {
				hh = left % 24;
				left = (int) left / 24;
				if (left > 0) {
					dd = left;
				}
			}
		}
		String diff = Integer.toString(mm) + "m " + Integer.toString(ss)
				+ "sec";
		return diff;
	}

	public static boolean hasEnoughTimePassed(long checkTime, int maxSeconds) {
		long CurrentTime = System.currentTimeMillis() / 1000L;
		return !((CurrentTime - checkTime) <= maxSeconds);
	}

}

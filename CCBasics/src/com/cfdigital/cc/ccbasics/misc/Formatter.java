package com.cfdigital.cc.ccbasics.misc;

public class Formatter {

	 public static String joinString(String[] string) {
		StringBuilder builder = new StringBuilder();
		for(String s : string) {
			builder.append(s + " ");
		}
		return builder.toString().trim();
	}
}
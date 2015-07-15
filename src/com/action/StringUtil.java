package com.action;


public class StringUtil {
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
}

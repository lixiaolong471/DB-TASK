package com.qianhtj.task.utils;

public class StringUtils {
	
	public static boolean isEmpty(String text){
		if(text !=null && !text.toString().trim().equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String text){
		return !isEmpty(text);
	}
	
}

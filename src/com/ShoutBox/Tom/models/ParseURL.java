package com.ShoutBox.Tom.models;

public final class ParseURL {
	
	public ParseURL(){
		
	}
	
	public static String[] parseURL(String url)
	{
		String [] urlArray = url.split("\\/");
		
		return urlArray;
	}

}

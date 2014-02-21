package com.ShoutBox.Tom.models;

public class ParseURL {
	
	public String[] parseURL(String url)
	{
		String [] urlArray = url.split("\\/");
		
		return urlArray;
	}

}

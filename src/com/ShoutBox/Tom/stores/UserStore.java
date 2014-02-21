package com.ShoutBox.Tom.stores;

public class UserStore {
String username;
/*
 * 	1 - regular
 * 	2 - admin
 */
int accessLevel;

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String val)
	{
		username = val;
	}
	
	public int getAccess()
	{
		return accessLevel;
	}
	
	public void setAccess(int val)
	{
		accessLevel = val;
	}
}

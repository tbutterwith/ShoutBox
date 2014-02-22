package com.ShoutBox.Tom.stores;

import java.util.*;

public class ProfileStore {
	String username;
	String password;
	String email;
	String secondaryEmail;
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	
	public String getSecondaryEmail()
	{
		return secondaryEmail;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setSecondaryEmail(String email)
	{
		this.secondaryEmail=email;
	}
}

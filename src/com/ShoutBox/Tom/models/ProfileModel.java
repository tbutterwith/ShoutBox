package com.ShoutBox.Tom.models;

import com.datastax.driver.core.*;
import com.ShoutBox.Tom.stores.*;

import java.util.*;

public class ProfileModel {
	private Cluster cluster;
	
	public void setCluster(Cluster cluster)
	{
		this.cluster=cluster;
	}
	
	public ProfileStore getProfileInfo(String username)
	{
		Session session = cluster.connect("ShoutBox");
		ProfileStore profile = new ProfileStore();

		PreparedStatement statement = session.prepare("SELECT * from users WHERE user = \'" + username + "\';");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
		System.out.println("No User Found");
		} else {
		for (Row row : rs) {
		profile.setUsername(row.getString("user"));
		}
		}
		session.close();
		
		return profile;
	}

	public ProfileStore getProfileDetail(String username)
	{
		Session session = cluster.connect("shoutbox");
		ProfileStore profile = new ProfileStore();

		PreparedStatement statement = session.prepare("SELECT * from users WHERE user = \'" + username + "\';");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No User Found");
		} else {
			for (Row row : rs) {
				profile.setUsername(row.getString("user"));
				profile.setPassword(row.getString("password"));
				profile.setEmail(row.getString("email"));
				profile.setSecondaryEmail(row.getString("secondaryEmail"));
			}
		}
		session.close();

		return profile;
	}
	
	public boolean checkPassword(String username, String pass)
	{
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("SELECT user from users WHERE user=\'" + username + "\' AND password =\'" + pass + "\';");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			session.close();
			return false;
		}
		else
		{
			session.close();
			return true;
		}
	}
	
	public void updateProfile(String username, String password, String primaryEmail, String secondaryEmail)
	{
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("DELETE FROM users WHERE user=\'" + username + "\';");

		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		statement = session.prepare("INSERT INTO users (user, password, email, secondaryEmail) VALUES (\'" +
				username + "\', \'" +  password + "\', \'" + primaryEmail + "\', \'" + secondaryEmail + "\');");

		boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}
	
	public void deleteUser(String username)
	{
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("DELETE FROM users WHERE user=\'" + username + "\';");

		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}
	
	public void deleteAllShouts(String username)
	{
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("DELETE FROM shouts WHERE user=\'" + username + "\';");

		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}
}

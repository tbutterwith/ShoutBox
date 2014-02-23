package com.ShoutBox.Tom.models;

import java.util.*;

import com.datastax.driver.core.*;

public class FollowerModel {
	Cluster cluster;
	
	public void setCluster(Cluster cluster)
	{
		this.cluster = cluster;
	}
	
	public Set<String> getFollowers(String user)
	{
		Set<String> followers = new TreeSet<String>();
		Session session = cluster.connect("shoutbox");
		
		PreparedStatement statement = session.prepare("SELECT following FROM followers WHERE user = \'" + user + "\';");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Followers returned");
			} else {
			for (Row row : rs) {
				followers.addAll(row.getSet(0, String.class));
				
			}
			}
		session.close();
		
		return followers;
	}
	
	public void newFollower(String user, String following)
	{
		Session session = cluster.connect("shoutbox");
		
		PreparedStatement statement = session.prepare("UPDATE followers SET following = following + {\'" + following + "\'}" +
				"WHERE user = \'" + user + "\';");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}
	
	public void deleteFollower(String user, String follower)
	{
		Session session = cluster.connect("shoutbox");
		
		PreparedStatement statement = session.prepare("UPDATE followers SET following = following - {\'" + follower + "\'}" +
				"WHERE user = \'" + user + "\';");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}

}

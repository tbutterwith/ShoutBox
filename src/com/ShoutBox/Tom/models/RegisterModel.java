package com.ShoutBox.Tom.models;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;

public class RegisterModel {
Cluster cluster;
	
	public void setCluster(Cluster cluster)
	{
		this.cluster = cluster;
	}
	
	public void newUser(String username, String email, String password)
	{
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("INSERT INTO users (user, password, email) VALUES (\'" +
		username + "\', \'" +  password + "\', {\'" + email + "\'});");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
		session.close();
	}
	
	public boolean checkUsername(String username)
	{
Session session = cluster.connect("shoutbox");
		
		PreparedStatement statement = session.prepare("SELECT user FROM users WHERE user = \'" + username + "\';");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			session.close();
			return false;
			} else {
				session.close();
				return true;
			}
	}
	
}

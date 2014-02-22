package com.ShoutBox.Tom.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class loginModel {
	
	Cluster cluster;
	
	public void setCluster(Cluster cluster)
	{
		this.cluster=cluster;
	}
	
	public Boolean checkLogin(String username, String pass)
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

}

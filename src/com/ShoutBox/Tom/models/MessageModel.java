package com.ShoutBox.Tom.models;

import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;

import com.ShoutBox.Tom.stores.*;

public class MessageModel {

	Cluster cluster;

	public void setCluster(Cluster casClust)
	{
		this.cluster = casClust;
	}
	
	public LinkedList<ShoutStore> getShouts(String user, String iD)
	{
		
		LinkedList<ShoutStore> shoutList = new LinkedList<ShoutStore>();
		Session session = cluster.connect("shoutbox");
		PreparedStatement statement = session.prepare("SELECT * from shouts");
		
		if(user != "")
			statement = session.prepare("SELECT * from shouts WHERE user =\'" + user + "\';");
		else if(iD != "")
			statement = session.prepare("SELECT * from shouts WHERE interaction_time =" + iD + " ALLOW FILTERING;");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
		System.out.println("No Shouts returned");
		} else {
		for (Row row : rs) {
		ShoutStore ts = new ShoutStore();
		ts.setShout(row.getString("shout"));
		ts.setUser(row.getString("user"));
		ts.setUuid(row.getUUID("interaction_time").toString());
		shoutList.add(ts);
		}
		}
		
		return shoutList;
	}
	
	public void newShout(String userName, String Message)
	{
		Session session = cluster.connect("shoutbox");
		
		String uuid = UUIDs.timeBased().toString();
		PreparedStatement statement = session.prepare("INSERT INTO shouts (user, interaction_time, shout) VALUES (\'" +
		userName + "\', " +  uuid + ", \'" + Message + "\');");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
	}
	
	public void deleteShout(String uuid)
	{
		Session session = cluster.connect("shoutbox");
		
		PreparedStatement statement = session.prepare("DELETE FROM shouts WHERE interaction_time = " + uuid);
		
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement);
	}
}

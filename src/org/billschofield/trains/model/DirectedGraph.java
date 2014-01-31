package org.billschofield.trains.model;

import org.billschofield.trains.model.route.RouteLeg;

import java.util.ArrayList;
import java.util.List;


public class DirectedGraph
{
	private List<Connection> connections = new ArrayList<Connection>();
	
	public void addConnection(Connection connection)
	{
		connections.add(connection);
	}

	public Connection findConnection(RouteLeg currentRouteLeg)
	{
		Connection foundConnection = null;
		for(Connection currentConnection : connections)
		{
			if (currentConnection.connects(currentRouteLeg))
			{
				foundConnection = currentConnection;
				break;
			}
		}
		return foundConnection;
	}


	public List<Connection> collectConnectionsOriginatingFrom(String startNode)
	{
		List<Connection> collectedConnections = new ArrayList<Connection>();
		for(Connection currentConnection : connections)
		{
			if (currentConnection.startsAt(startNode))
			{
				collectedConnections.add(currentConnection);
			}
		}
		return collectedConnections;
	}
}

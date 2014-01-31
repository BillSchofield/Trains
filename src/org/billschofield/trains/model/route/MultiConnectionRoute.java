package org.billschofield.trains.model.route;

import org.billschofield.trains.model.Connection;

import java.util.ArrayList;
import java.util.List;

public class MultiConnectionRoute implements Route
{
	private List<Connection> connections = new ArrayList<Connection>();
	
	public MultiConnectionRoute()
	{
	}

	public MultiConnectionRoute(MultiConnectionRoute route)
	{
		connections.addAll(route.connections);
	}

	public void addConnection(Connection connection)
	{
		connections.add(connection);
	}

	@Override 
	public boolean equals(Object other) 
	{
	    boolean result = false;
	    if (other instanceof MultiConnectionRoute) 
	    {
	    	MultiConnectionRoute that = (MultiConnectionRoute)other;
    		result = this.connections.equals(that.connections);
	    }
	    return result;
	}
	
	@Override
	public RouteLength getLength()
	{
		RouteLength totalLength = new RouteLength(0);
		for (Connection currentConnection : connections)
		{
			totalLength = totalLength.plus(currentConnection.getLength());
		}
		return totalLength;
	}

	@Override
	public int getNumberOfStops()
	{
		return connections.size();
	}

	@Override
	public String getEndNode()
	{
		int size = connections.size();
		if (size > 0)
		{
			return connections.get(size-1).getEndNode();
		}
		return null;
	}
}

package org.billschofield.trains;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.route.Route;
import org.billschofield.trains.model.route.RouteLength;

public class FakeRoute implements Route
{
	private RouteLength length;
	private int stops;
	private String endNode;

	public FakeRoute(int length, String endNode)
	{
		this.length = new RouteLength(length);
		this.stops = length;
		this.endNode = endNode;
	}

	@Override
	public void addConnection(Connection connection)
	{
	}

	@Override
	public RouteLength getLength()
	{
		return length;
	}

	@Override
	public int getNumberOfStops()
	{
		return stops;
	}

	@Override
	public String getEndNode()
	{
		return endNode;
	}
}

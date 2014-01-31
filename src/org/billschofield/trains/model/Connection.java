package org.billschofield.trains.model;

import org.billschofield.trains.model.route.RouteLength;
import org.billschofield.trains.model.route.RouteLeg;

public class Connection
{
	private RouteLeg routeLeg;
	private RouteLength length;
	
	public Connection(RouteLeg routeLeg, RouteLength length)
	{
		this.routeLeg = routeLeg;
		this.length = length;
	}

	public boolean connects(RouteLeg routeLeg)
	{
		return this.routeLeg.getStartNode().equals(routeLeg.getStartNode()) &&
				this.routeLeg.getEndNode().equals(routeLeg.getEndNode());
	}
	
	public RouteLength getLength()
	{
		return length;
	}

	public boolean startsAt(String startNode)
	{
		return routeLeg.getStartNode() == startNode;
	}

	public String getEndNode()
	{
		return routeLeg.getEndNode();
	}
}

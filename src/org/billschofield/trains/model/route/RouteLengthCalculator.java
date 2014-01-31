package org.billschofield.trains.model.route;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.DirectedGraph;

import java.util.List;


public class RouteLengthCalculator
{
	private DirectedGraph graph;
	
	public RouteLengthCalculator(DirectedGraph graph)
	{
		this.graph = graph;
	}

	public Object calculate(List<RouteLeg> route)
	{
		RouteLength length = new RouteLength(0);
		for (RouteLeg currentRouteLeg : route)
		{
			Connection foundConnection = graph.findConnection(currentRouteLeg);
			if (foundConnection == null)
			{
				return new RouteLength();
			}
			length = length.plus(foundConnection.getLength());
		}
		return length;
	}

}

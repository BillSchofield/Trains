package org.billschofield.trains.reporters;

import org.billschofield.trains.collectors.RouteCollector;
import org.billschofield.trains.collectors.ShortestDistanceRouteCollector;
import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.model.route.RouteExpander;
import org.billschofield.trains.model.route.RouteLength;

public class ShortestTripLengthReporter
{
	private DirectedGraph graph;
	
	public ShortestTripLengthReporter(DirectedGraph graph)
	{
		this.graph = graph;
	}

	public String report(String startNode, String endNode)
	{
		RouteLength lengthOfShortestRoute = new RouteLength();
		RouteCollector collector = new ShortestDistanceRouteCollector();
		new RouteExpander(graph, collector).expand(startNode, endNode);
		if (collector.getRoutes().size() > 0)
		{
			lengthOfShortestRoute = collector.getRoutes().get(0).getLength();
		}
		return lengthOfShortestRoute.toString();
	}
}

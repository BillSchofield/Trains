package org.billschofield.trains.reporters;

import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.collectors.RouteCollector;
import org.billschofield.trains.model.route.RouteExpander;

public class MatchingTripReporter
{
	private DirectedGraph graph;
	
	public MatchingTripReporter(DirectedGraph graph)
	{
		this.graph = graph;
	}

	public String report(RouteCollector collector, String startNode, String endNode)
	{
		new RouteExpander(graph, collector).expand(startNode, endNode);
		return Integer.toString(collector.getRoutes().size());
	}
}

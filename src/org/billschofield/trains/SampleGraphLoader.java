package org.billschofield.trains;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.model.route.RouteLeg;
import org.billschofield.trains.model.route.RouteLength;

public class SampleGraphLoader
{
	public DirectedGraph create()
	{
		DirectedGraph graph = new DirectedGraph();
		
		addConnection(graph, "A", "B", 5);
		addConnection(graph, "B", "C", 4);
		addConnection(graph, "C", "D", 8);
		addConnection(graph, "D", "C", 8);
		addConnection(graph, "D", "E", 6);
		addConnection(graph, "A", "D", 5);
		addConnection(graph, "C", "E", 2);
		addConnection(graph, "E", "B", 3);
		addConnection(graph, "A", "E", 7);
		
		return graph;
	}
	
	private void addConnection(DirectedGraph graph, String fromNode, String toNode, int legLength)
	{
		graph.addConnection(new Connection(new RouteLeg(fromNode, toNode), new RouteLength(legLength)));
	}
}

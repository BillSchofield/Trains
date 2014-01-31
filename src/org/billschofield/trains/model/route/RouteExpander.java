package org.billschofield.trains.model.route;

import org.billschofield.trains.collectors.RouteCollector;
import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.DirectedGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//
// org.billschofield.trains.model.route.RouteExpander traverses a directed graph trying to find a route from the given startNode to the endNode.
// It uses a route collector to limit this expansion and to hold the routes that it finds connecting the start 
// and end nodes. 
// Breadth first expansion is used to avoid getting trapped in infinite cycles. This is important because some of the desired
// routes will have a bounded number of cycles.
//

public class RouteExpander
{
	private DirectedGraph graph;
	private RouteCollector routeCollector;
	
	public RouteExpander(DirectedGraph graph, RouteCollector routeCollector)
	{
		this.graph = graph;
		this.routeCollector = routeCollector;
	}

	public void expand(String startNode, String endNode)
	{
		Queue<MultiConnectionRoute> queue = new LinkedList<MultiConnectionRoute>();
		expandRoute(queue, startNode, endNode, new MultiConnectionRoute());
	}

	private void expandRoute(Queue<MultiConnectionRoute> queue, String startNode, String endNode, MultiConnectionRoute route)
	{
		List<Connection> expandedConnections = graph.collectConnectionsOriginatingFrom(startNode);
		for (Connection currentConnection : expandedConnections)
		{
			MultiConnectionRoute newRoute = new MultiConnectionRoute(route);
			newRoute.addConnection(currentConnection);
			queue.add(newRoute);
		}
		while (!queue.isEmpty())
		{
			MultiConnectionRoute currentRoute = queue.remove();
			if (routeCollector.withinLimit(currentRoute))
			{
				String currentEndNode = currentRoute.getEndNode();
				if (currentEndNode == endNode)
				{
					routeCollector.addCandidate(currentRoute);
				}
				
				expandRoute(queue, currentEndNode, endNode, currentRoute);
			}
		}
	}
}

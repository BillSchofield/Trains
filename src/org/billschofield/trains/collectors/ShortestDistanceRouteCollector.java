package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;
import org.billschofield.trains.model.route.RouteLength;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceRouteCollector implements RouteCollector
{
	private List<Route> routes = new ArrayList<Route>();
	
	@Override
	public void addCandidate(Route route)
	{
		if (routes.size() == 0 || route.getLength().lessThan(currentShortestLength()))
		{
			routes.clear();
			routes.add(route);
		}
	}

	@Override
	public List<Route> getRoutes()
	{
		return routes;
	}

	@Override
	public boolean withinLimit(Route route)
	{
		return route.getLength().lessThan(currentShortestLength());
	}

	private RouteLength currentShortestLength()
	{
		RouteLength shortestRouteLength = new RouteLength();
		if (routes.size() > 0)
		{
			shortestRouteLength = routes.get(0).getLength();
		}
		return shortestRouteLength;
	}
}

package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;

import java.util.ArrayList;
import java.util.List;

public class MaximumOfNStopsRouteCollector implements RouteCollector
{
	private int limit;
	private List<Route> routes;
	
	public MaximumOfNStopsRouteCollector(int limit)
	{
		this.limit = limit;
		routes = new ArrayList<Route>();
	}
	
	@Override
	public boolean withinLimit(Route route)
	{
		return route.getNumberOfStops() <= limit;
	}

	@Override
	public void addCandidate(Route route)
	{
		if (withinLimit(route))
		{
			routes.add(route);
		}
	}

	@Override
	public List<Route> getRoutes()
	{
		return routes;
	}
}

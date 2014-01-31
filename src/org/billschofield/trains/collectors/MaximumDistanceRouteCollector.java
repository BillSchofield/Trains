package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;
import org.billschofield.trains.model.route.RouteLength;

import java.util.ArrayList;
import java.util.List;

public class MaximumDistanceRouteCollector implements RouteCollector
{
	private List<Route> routes = new ArrayList<Route>();
	private RouteLength limit;
	
	public MaximumDistanceRouteCollector(int limit)
	{
		this.limit = new RouteLength(limit);
	}

	@Override
	public void addCandidate(Route route)
	{
		if (route.getLength().lessThan(limit))
		{
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
		return route.getLength().lessThan(limit);
	}

}

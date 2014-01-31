package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;

import java.util.ArrayList;
import java.util.List;

public class ExactlyNStopsRouteCollector implements RouteCollector
{
	private int limit;
	private List<Route> routes;

	public ExactlyNStopsRouteCollector(int limit)
	{
		this.limit = limit;
		routes = new ArrayList<Route>();
	}

	@Override
	public void addCandidate(Route route)
	{
		if (route.getNumberOfStops() == limit)
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
		return route.getNumberOfStops() <= limit;
	}
}

package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;

import java.util.ArrayList;
import java.util.List;


public class UnfilteredRouteCollector implements RouteCollector
{
	List<Route> routes = new ArrayList<Route>();

	@Override
	public void addCandidate(Route route)
	{
		routes.add(route);
	}

	@Override
	public List<Route> getRoutes()
	{
		return routes;
	}

	@Override
	public boolean withinLimit(Route route)
	{
		return true;
	}
}

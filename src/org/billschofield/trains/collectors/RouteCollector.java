package org.billschofield.trains.collectors;

import org.billschofield.trains.model.route.Route;

import java.util.List;

//
// RouteCollectors are used to limit graph expansion and collect/filter routes that are added to it. 
// These two jobs are relatively coupled and it is simpler to keep them in one interface for now.
//

public interface RouteCollector
{
	boolean withinLimit(Route route);
	void addCandidate(Route route);
	List<Route> getRoutes();
}

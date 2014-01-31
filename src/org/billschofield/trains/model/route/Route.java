package org.billschofield.trains.model.route;

import org.billschofield.trains.model.Connection;

public interface Route
{
	public abstract void addConnection(Connection connection);
	public abstract RouteLength getLength();
	public abstract int getNumberOfStops();
	public abstract String getEndNode();
}
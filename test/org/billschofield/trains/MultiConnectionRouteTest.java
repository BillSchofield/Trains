package org.billschofield.trains;

import static org.junit.Assert.assertEquals;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.route.MultiConnectionRoute;
import org.billschofield.trains.model.route.RouteLeg;
import org.billschofield.trains.model.route.RouteLength;
import org.junit.Before;
import org.junit.Test;

public class MultiConnectionRouteTest
{
	private MultiConnectionRoute route;
	final private RouteLeg routeLeg = new RouteLeg("", "");
	final private RouteLength routeLength1 = new RouteLength(3);
	final private RouteLength routeLength2 = new RouteLength(4);

	@Test
	public void testLengthOfEmptyRouteIsZero() 
	{
		assertEquals(new RouteLength(0), route.getLength());
	}

	@Test
	public void testLengthOfRouteWithOneConnectionIsTheLengthOfTheConnection() 
	{
		route.addConnection(new Connection(routeLeg, routeLength1));
		assertEquals(routeLength1, route.getLength());
	}

	@Test
	public void testLengthOfRouteWithTwoConnectionIsSumOfLengthOfTheConnections() 
	{
		route.addConnection(new Connection(routeLeg, routeLength1));
		route.addConnection(new Connection(routeLeg, routeLength2));
		
		assertEquals(routeLength1.plus(routeLength2), route.getLength());
	}

	@Before
	public void setUp() throws Exception 
	{
		route = new MultiConnectionRoute();
	}
}

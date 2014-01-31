package org.billschofield.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.billschofield.trains.collectors.RouteCollector;
import org.billschofield.trains.collectors.ShortestDistanceRouteCollector;
import org.billschofield.trains.model.route.Route;
import org.junit.Before;
import org.junit.Test;

public class ShortestDistanceRouteCollectorTest
{
	final private int lengthOfShortestRoute = 5;
	final private Route shortRoute = new FakeRoute(lengthOfShortestRoute, "EndOfRoute");
	final private Route longRoute = new FakeRoute(lengthOfShortestRoute + 1, "EndOfRoute");
	private RouteCollector collector;
	
	@Test
	public void testSingleCandidateRouteIsCollected() 
	{
		collector.addCandidate(shortRoute);
		
		assertTrue(collector.getRoutes().contains(shortRoute));
	}

	@Test
	public void testLongerRouteIsNotCollected() 
	{
		collector.addCandidate(shortRoute);
		collector.addCandidate(longRoute);
		
		assertFalse(collector.getRoutes().contains(longRoute));
	}

	@Test
	public void testShorterRouteIsCollected() 
	{
		collector.addCandidate(longRoute);
		collector.addCandidate(shortRoute);
		
		assertTrue(collector.getRoutes().contains(shortRoute));
	}
	
	@Test
	public void testShorterRouteIsOnlyRouteCollected() 
	{
		collector.addCandidate(longRoute);
		collector.addCandidate(shortRoute);
		
		assertEquals(1, collector.getRoutes().size());
	}
	
	@Test
	public void testShorterRouteIsWithinLimitSetByAddingLongerRoute() 
	{
		collector.addCandidate(longRoute);
		
		assertTrue(collector.withinLimit(shortRoute));
	}
	
	@Test
	public void testLongerRouteIsNotWithinLimitSetByAddingShorterRoute() 
	{
		collector.addCandidate(shortRoute);
		
		assertFalse(collector.withinLimit(longRoute));
	}
	
	@Before
	public void setUp() throws Exception 
	{
		collector = new ShortestDistanceRouteCollector();
	}
}

package org.billschofield.trains;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.billschofield.trains.collectors.ExactlyNStopsRouteCollector;
import org.junit.Before;
import org.junit.Test;

public class ExactlyNStopsRouteCollectorTest
{
	final private int targetRouteStops = 4;
	final private FakeRoute shortRoute = new FakeRoute(targetRouteStops - 1, "EndNode");
	final private FakeRoute justRightRoute = new FakeRoute(targetRouteStops, "EndNode");
	final private FakeRoute longRoute = new FakeRoute(targetRouteStops + 1, "EndNode");
	private ExactlyNStopsRouteCollector collector;

	@Test
	public void testRouteWithTooFewStopsIsNotCollected() 
	{
		collector.addCandidate(shortRoute);
		assertFalse(collector.getRoutes().contains(shortRoute));
	}

	@Test
	public void testRouteWithCorrectNumberOfStopsIsCollected() 
	{
		collector.addCandidate(justRightRoute);
		assertTrue(collector.getRoutes().contains(justRightRoute));
	}

	@Test
	public void testRouteWithTooManyStopsIsNotCollected() 
	{
		collector.addCandidate(longRoute);
		assertFalse(collector.getRoutes().contains(longRoute));
	}

	@Test
	public void testRouteWithCorrectNumberOfStopsIsWithinLimit() 
	{
		assertTrue(collector.withinLimit(justRightRoute));
	}

	@Test
	public void testRouteWithTooManyStopsIsNotWithinLimit() 
	{
		assertFalse(collector.withinLimit(longRoute));
	}

	@Before
	public void setUp() throws Exception 
	{
		collector = new ExactlyNStopsRouteCollector(targetRouteStops);
	}
}

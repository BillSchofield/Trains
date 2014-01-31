package org.billschofield.trains;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.billschofield.trains.collectors.MaximumDistanceRouteCollector;
import org.junit.Before;
import org.junit.Test;

public class MaximumDistanceRouteCollectorTest
{
	final private int targetRouteLength = 10;
	final private FakeRoute shortRoute = new FakeRoute(targetRouteLength - 1, "EndNode");
	final private FakeRoute justRightRoute = new FakeRoute(targetRouteLength, "EndNode");
	private MaximumDistanceRouteCollector collector;

	@Test
	public void testRouteOfLessThanTargetDistanceIsCollected() 
	{
		collector.addCandidate(shortRoute);
		assertTrue(collector.getRoutes().contains(shortRoute));
	}

	@Test
	public void testRouteOfTargetDistanceIsNotCollected() 
	{
		collector.addCandidate(justRightRoute);
		assertFalse(collector.getRoutes().contains(justRightRoute));
	}
	
	@Test
	public void testRouteOfLessThanTargetDistanceIsWithinLimit() 
	{
		assertTrue(collector.withinLimit(shortRoute));
	}

	@Test
	public void testRouteOfTargetDistanceIsNotWithinLimit() 
	{
		assertFalse(collector.withinLimit(justRightRoute));
	}

	@Before
	public void setUp() throws Exception 
	{
		collector = new MaximumDistanceRouteCollector(targetRouteLength);
	}
}

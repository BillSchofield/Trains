package org.billschofield.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.billschofield.trains.model.route.RouteLength;
import org.junit.Test;

public class RouteLengthTest
{
	final private RouteLength uninitializedRoute = new RouteLength();
	final private RouteLength routeLength0 = new RouteLength(0);
	final private RouteLength routeLength1 = new RouteLength(1);
	final private RouteLength routeLength2 = new RouteLength(2);
	final private RouteLength routeLength3 = new RouteLength(3);
	final private RouteLength routeLength5 = new RouteLength(5);

	@Test
	public void testUninitializedRouteLengthReportsNoRoute() 
	{
		assertEquals("NO SUCH ROUTE", uninitializedRoute.toString());
	}

	@Test
	public void testRouteOfLengthZeroReportsLengthZero() 
	{
		assertEquals("0", routeLength0.toString());
	}

	@Test
	public void testAddingLength1And2RoutesYieldsLength3Route() 
	{
		assertEquals(routeLength3, routeLength1.plus(routeLength2));
	}
	
	@Test
	public void testAddingLength2And3RoutesYieldsLength5Route() 
	{
		assertEquals(routeLength5, routeLength2.plus(routeLength3));
	}

	@Test
	public void testAddingToUninitializedRouteLengthYieldsUninitializedRoute() 
	{
		assertEquals(uninitializedRoute, uninitializedRoute.plus(routeLength1));
	}

	@Test
	public void testAddingUninitializedRouteLengthToOtherRouteLengthYieldsUninitializedRoute() 
	{
		assertEquals(uninitializedRoute, routeLength1.plus(uninitializedRoute));
	}

	@Test
	public void testUninitializedRouteLengthIsLessThanUninitializedRouteLength() 
	{
		assertTrue(uninitializedRoute.lessThan(uninitializedRoute));
	}

	@Test
	public void testRouteLengthZeroIsLessThanUninitializedRouteLength() 
	{
		assertTrue(routeLength0.lessThan(uninitializedRoute));
	}

	@Test
	public void testUninitializedRouteLengthIsNotLessThanRouteLengthZero() 
	{
		assertFalse(uninitializedRoute.lessThan(routeLength0));
	}

	@Test
	public void testRouteLengthZeroIsNotLessThanRouteLengthZero() 
	{
		assertFalse(routeLength0.lessThan(routeLength0));
	}
	
	@Test
	public void testRouteLengthZeroIsLessThanRouteLengthOne() 
	{
		assertTrue(routeLength0.lessThan(routeLength1));
	}

	@Test
	public void testRouteLengthOneIsNotLessThanRouteLengthZero() 
	{
		assertFalse(routeLength1.lessThan(routeLength0));
	}
}

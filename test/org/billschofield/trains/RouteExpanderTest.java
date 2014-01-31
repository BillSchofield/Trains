package org.billschofield.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.billschofield.trains.collectors.MaximumOfNStopsRouteCollector;
import org.billschofield.trains.collectors.RouteCollector;
import org.billschofield.trains.model.*;
import org.billschofield.trains.model.route.*;
import org.junit.Before;
import org.junit.Test;


public class RouteExpanderTest
{
	private DirectedGraph graph;
	private RouteExpander expander;
	private RouteCollector routeCollector;
	private Route routeABC;
	private Route routeADC;
	private Route routeABEC;
	private Route routeABCDC;
	final private int maximumNumberOfStops = 4;
	final private Connection connectionAB = new Connection(new RouteLeg("A", "B"), new RouteLength());
	final private Connection connectionBC = new Connection(new RouteLeg("B", "C"), new RouteLength());
	final private Connection connectionCD = new Connection(new RouteLeg("C", "D"), new RouteLength());
	final private Connection connectionDE = new Connection(new RouteLeg("D", "E"), new RouteLength());
	final private Connection connectionEF = new Connection(new RouteLeg("E", "F"), new RouteLength());
	final private Connection connectionAD = new Connection(new RouteLeg("A", "D"), new RouteLength());
	final private Connection connectionDC = new Connection(new RouteLeg("D", "C"), new RouteLength());
	final private Connection connectionBE = new Connection(new RouteLeg("B", "E"), new RouteLength());
	final private Connection connectionEC = new Connection(new RouteLeg("E", "C"), new RouteLength());

	@Test
	public void testSinglePossibleRouteGivesCollectionWithOneRoute() 
	{
		graph.addConnection(connectionAB);
		
		expander.expand("A", "B");
		
		assertEquals(1, routeCollector.getRoutes().size());
	}

	@Test
	public void testSingleLegRouteIsCollected() 
	{
		graph.addConnection(connectionAB);
		
		expander.expand("A", "B");
		Route desiredRoute = new MultiConnectionRoute();
		desiredRoute.addConnection(connectionAB);
		assertTrue(routeCollector.getRoutes().contains(desiredRoute));
	}

	@Test
	public void testMultiLegRouteIsCollected() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		expander.expand("A", "C");
		assertTrue(routeCollector.getRoutes().contains(routeABC));
	}

	@Test
	public void testBothRoutesFoundInDiamondGraph() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		graph.addConnection(connectionAD);
		graph.addConnection(connectionDC);
		
		expander.expand("A", "C");

		assertTrue(routeCollector.getRoutes().contains(routeABC));
		assertTrue(routeCollector.getRoutes().contains(routeADC));
	}

	@Test
	public void testRouteWithBranchAfterFirstNodeIsCollected() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		graph.addConnection(connectionBE);
		graph.addConnection(connectionEC);
		
		expander.expand("A", "C");
		assertTrue(routeCollector.getRoutes().contains(routeABEC));
	}

	@Test
	public void testRoutesNotAllowedByCollectorIsNotCollected() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		graph.addConnection(connectionCD);
		graph.addConnection(connectionDE);
		graph.addConnection(connectionEF);
		
		expander.expand("A", "F");
		assertEquals(0, routeCollector.getRoutes().size());
	}

	@Test
	public void testRouteThatIncludesGoalNodeTwiceIsCollected() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		graph.addConnection(connectionCD);
		graph.addConnection(connectionDC);
		
		expander.expand("A", "C");
		assertTrue(routeCollector.getRoutes().contains(routeABCDC));
	}

	@Before
	public void setUp() throws Exception 
	{
		graph = new DirectedGraph();
		routeCollector = new MaximumOfNStopsRouteCollector(maximumNumberOfStops);
		expander = new RouteExpander(graph, routeCollector);
		
		routeABC = new MultiConnectionRoute();
		routeABC.addConnection(connectionAB);
		routeABC.addConnection(connectionBC);

		routeADC = new MultiConnectionRoute();
		routeADC.addConnection(connectionAD);
		routeADC.addConnection(connectionDC);
		
		routeABEC = new MultiConnectionRoute();
		routeABEC.addConnection(connectionAB);
		routeABEC.addConnection(connectionBE);
		routeABEC.addConnection(connectionEC);
		
		routeABCDC = new MultiConnectionRoute();
		routeABCDC.addConnection(connectionAB);
		routeABCDC.addConnection(connectionBC);
		routeABCDC.addConnection(connectionCD);
		routeABCDC.addConnection(connectionDC);
	}
}

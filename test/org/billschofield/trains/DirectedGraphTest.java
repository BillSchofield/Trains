package org.billschofield.trains;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.model.route.RouteLeg;
import org.billschofield.trains.model.route.RouteLength;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectedGraphTest
{
	private DirectedGraph graph;
	final private RouteLeg routeLegAB = new RouteLeg("A", "B");
	final private RouteLeg routeLegBC = new RouteLeg("B", "C");
	final private RouteLeg routeLegAC = new RouteLeg("A", "C");
	final private Connection connectionAB = new Connection(routeLegAB, new RouteLength());
	final private Connection connectionBC = new Connection(routeLegBC, new RouteLength());
	final private Connection connectionAC = new Connection(routeLegAC, new RouteLength());
	
	@Test
	public void testConnectionContainingDesiredRouteLegIsFound() 
	{
		graph.addConnection(connectionAB);
		assertEquals(connectionAB, graph.findConnection(routeLegAB));
	}
	
	@Test
	public void testConnectionContainingDesiredRouteLegIsFoundFromMultpleConnections() 
	{
		graph.addConnection(connectionBC);
		graph.addConnection(connectionAB);
		assertEquals(connectionAB, graph.findConnection(routeLegAB));
	}

	@Test
	public void testSingleConnectionFromStartNodeIsCollected() 
	{
		graph.addConnection(connectionAB);
		assertTrue(graph.collectConnectionsOriginatingFrom("A").contains(connectionAB));
	}

	@Test
	public void testConnectionFromStartNodeIsCollectedFromGraphWithMultipleConnections() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		assertTrue(graph.collectConnectionsOriginatingFrom("A").contains(connectionAB));
	}

	@Test
	public void testTwoConnectionAreCollectedFromGraphWithTwoConnectionsStartingAtNode() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionAC);
		assertEquals(2, graph.collectConnectionsOriginatingFrom("A").size());
	}

	@Test
	public void testSearchingForMissingConnectionReturnsNull() 
	{
		assertEquals(null, graph.findConnection(routeLegAB));
	}

	@Before
	public void setUp() throws Exception 
	{
		graph = new DirectedGraph();
	}

}

package org.billschofield.trains;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.billschofield.trains.model.Connection;
import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.model.route.RouteLeg;
import org.billschofield.trains.model.route.RouteLength;
import org.billschofield.trains.model.route.RouteLengthCalculator;
import org.junit.Before;
import org.junit.Test;



public class RouteLengthCalculatorTest 
{
	private RouteLengthCalculator lengthCalculator;
	private DirectedGraph graph;
	private List<RouteLeg> route;
	
	final private RouteLeg legAB = new RouteLeg("A", "B");
	final private RouteLeg legBC = new RouteLeg("B", "C");
	final private int lengthOfConnectionAB = 3;
	final private int lengthOfConnectionBC = 4;
	final private RouteLength routeLengthAB = new RouteLength(lengthOfConnectionAB);
	final private RouteLength routeLengthBC = new RouteLength(lengthOfConnectionBC);
	final private Connection connectionAB = new Connection(legAB, routeLengthAB);
	final private Connection connectionBC = new Connection(legBC, routeLengthBC);
	final private Connection connectionDE = new Connection(new RouteLeg("D", "E"), new RouteLength(13));
	

	@Test
	public void testLengthOfEmptyRouteYieldsZeroRouteLength() 
	{
		assertEquals(new RouteLength(0), lengthCalculator.calculate(route));
	}


	@Test
	public void testLengthOfRouteWithOneConnection() 
	{
		graph.addConnection(connectionAB);
		route.add(legAB);
		assertEquals(routeLengthAB, lengthCalculator.calculate(route));
	}

	@Test
	public void testLengthOfRouteWithTwoConnections() 
	{
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		route.add(legAB);
		route.add(legBC);
		assertEquals(routeLengthAB.plus(routeLengthBC), lengthCalculator.calculate(route));
	}
	
	@Test
	public void testLengthOfRouteWithTwoConnectionsWhenGraphContainsUnusedConnections() 
	{
		graph.addConnection(connectionDE);
		graph.addConnection(connectionAB);
		graph.addConnection(connectionBC);
		route.add(legAB);
		route.add(legBC);
		assertEquals(routeLengthAB.plus(routeLengthBC), lengthCalculator.calculate(route));
	}
	
	@Test
	public void testLengthOfUnconnectedRouteYieldsDefaultRouteLength() 
	{
		graph.addConnection(connectionAB);
		route.add(legAB);
		route.add(legBC);
		assertEquals(new RouteLength(), lengthCalculator.calculate(route));
	}

	@Before
	public void setUp() throws Exception 
	{
		route = new ArrayList<RouteLeg>();
		graph = new DirectedGraph();
		lengthCalculator = new RouteLengthCalculator(graph);
	}
}

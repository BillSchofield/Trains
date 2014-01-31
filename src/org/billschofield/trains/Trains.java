package org.billschofield.trains;

import org.billschofield.trains.collectors.ExactlyNStopsRouteCollector;
import org.billschofield.trains.collectors.MaximumDistanceRouteCollector;
import org.billschofield.trains.collectors.MaximumOfNStopsRouteCollector;
import org.billschofield.trains.model.DirectedGraph;
import org.billschofield.trains.model.route.RouteLengthCalculator;
import org.billschofield.trains.reporters.MatchingTripReporter;
import org.billschofield.trains.reporters.RouteLengthReporter;
import org.billschofield.trains.reporters.ShortestTripLengthReporter;

public class Trains
{
	public static void main(String[] args)
	{
		DirectedGraph graph = new SampleGraphLoader().create();
		RouteLengthReporter routeLengthReporter = new RouteLengthReporter(new RouteLengthCalculator(graph));
		MatchingTripReporter matchingTripReporter = new MatchingTripReporter(graph);
		ShortestTripLengthReporter shortestTripReporter = new ShortestTripLengthReporter(graph);
		
		printReportLine(1, routeLengthReporter.report("ABC"));
		printReportLine(2, routeLengthReporter.report("AD"));
		printReportLine(3, routeLengthReporter.report("ADC"));
		printReportLine(4, routeLengthReporter.report("AEBCD"));
		printReportLine(5, routeLengthReporter.report("AED"));
		printReportLine(6, matchingTripReporter.report(new MaximumOfNStopsRouteCollector(3), "C", "C"));
		printReportLine(7, matchingTripReporter.report(new ExactlyNStopsRouteCollector(4), "A", "C"));
		printReportLine(8, shortestTripReporter.report("A", "C"));
		printReportLine(9, shortestTripReporter.report("B", "B"));
		printReportLine(10, matchingTripReporter.report(new MaximumDistanceRouteCollector(30), "C", "C"));

		System.out.println("==========");
	}
	
	private static void printReportLine(int outputLineNumber, String reportedValue)
	{
		System.out.println("Output #" + outputLineNumber+ ": " + reportedValue);
	}
}


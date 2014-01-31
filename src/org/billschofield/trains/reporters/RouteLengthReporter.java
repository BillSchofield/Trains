package org.billschofield.trains.reporters;

import org.billschofield.trains.model.route.RouteLeg;
import org.billschofield.trains.model.route.RouteLengthCalculator;

import java.util.ArrayList;
import java.util.List;


public class RouteLengthReporter
{
	private RouteLengthCalculator routeLengthCalculator;
	
	public RouteLengthReporter(RouteLengthCalculator routeLengthCalculator)
	{
		this.routeLengthCalculator = routeLengthCalculator;
	}

	public String report(String route)
	{
		return routeLengthCalculator.calculate(createRoute(route)).toString();
	}
	
	private List<RouteLeg> createRoute(String string)
	{
		List<RouteLeg> route = new ArrayList<RouteLeg>();
		for (int index = 1; index < string.length(); index++)
		{
			route.add(new RouteLeg(string.substring(index-1, index), string.substring(index, index+1)));
		}
		
		return route;
	}
}

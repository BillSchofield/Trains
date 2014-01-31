package org.billschofield.trains.model.route;

public class RouteLeg
{
	private String startNode;
	private String endNode;

	public RouteLeg(String startNode, String endNode)
	{
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	public String getStartNode()
	{
		return startNode;
	}

	public String getEndNode()
	{
		return endNode;
	}
	
	@Override 
	public boolean equals(Object other) 
	{
	    boolean result = false;
	    if (other instanceof RouteLeg) 
	    {
	    	RouteLeg that = (RouteLeg) other;
	        result = this.startNode == that.startNode && this.endNode == that.endNode;
	    }
	    return result;
	}
}

package org.billschofield.trains.model.route;

public class RouteLength
{
	final private static int invalidLength = -1; 
	private int length = 0;
	
    public RouteLength()
	{
    	length = invalidLength;
	}

    public RouteLength(int length)
	{
    	this.length = length;
	}
	
    @Override 
    public boolean equals(Object other) 
    {
        boolean result = false;
        if (other instanceof RouteLength) 
        {
        	RouteLength that = (RouteLength) other;
            result = this.length == that.length;
        }
        return result;
    }

    public String toString() 
    {
    	if (uninitialized())
    	{
    		return "NO SUCH ROUTE";
    	}
		return Integer.toString(length);
	}

	public RouteLength plus(RouteLength other)
	{
		if (uninitialized() || other.uninitialized())
		{
			return new RouteLength();
		}
		return new RouteLength(this.length + other.length);
	}

	public boolean lessThan(RouteLength other)
	{
		boolean result = false;
		if (other.uninitialized())
		{
			result = true;
		}
		else if (!uninitialized())
		{
			result = length < other.length;
		}
		return result;
	}
	
	private boolean uninitialized()
	{
		return length == invalidLength;
	}
}

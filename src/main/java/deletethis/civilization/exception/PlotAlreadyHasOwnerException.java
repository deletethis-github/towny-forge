package deletethis.civilization.exception;

import deletethis.civilization.Plot;
import deletethis.civilization.Town;

public class PlotAlreadyHasOwnerException extends Exception
{
	private static final long serialVersionUID = 3247408799121731744L;
	private Town town;
	private Plot plot;
	
	public PlotAlreadyHasOwnerException(Town town, Plot plot)
	{
		setTown(town);
		setPlot(plot);
	}
	
	public void setTown(Town town)
	{
		this.town = town;
	}
	
	public Town getTown()
	{
		return town;
	}
	
	public void setPlot(Plot plot)
	{
		this.plot = plot;
	}
	
	public Plot getPlot()
	{
		return plot;
	}
}

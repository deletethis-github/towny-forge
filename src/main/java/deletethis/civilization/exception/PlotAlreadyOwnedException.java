package deletethis.civilization.exception;

import deletethis.civilization.Plot;
import deletethis.civilization.Town;

public class PlotAlreadyOwnedException extends Exception
{
	private static final long serialVersionUID = 6417998817146315881L;
	private Town town;
	private Plot plot;
	
	public PlotAlreadyOwnedException(Town town, Plot plot)
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

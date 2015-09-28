package deletethis.civilization.exception;

import deletethis.civilization.Town;

public class TownAlreadyExistsException extends Exception
{
	private static final long serialVersionUID = 4967435377063343813L;
	private Town town;
	
	public TownAlreadyExistsException(Town town)
	{
		setTown(town);
	}
	
	public void setTown(Town town)
	{
		this.town = town;
	}
	
	public Town getTown()
	{
		return town;
	}
}

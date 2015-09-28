package deletethis.civilization.exception;

import deletethis.civilization.Town;

public class TownDoesNotExistException extends Exception
{
	private static final long serialVersionUID = -3218438269446275073L;
	private Town town;
	
	public TownDoesNotExistException(Town town)
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

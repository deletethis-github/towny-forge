package deletethis.civilization.exception;

import deletethis.civilization.Resident;
import deletethis.civilization.Town;

public class ResidentAlreadyRegisteredException extends Exception
{
	private static final long serialVersionUID = -8936717534099035177L;
	private Resident resident;
	private Town town;
	
	public ResidentAlreadyRegisteredException(Resident resident, Town town)
	{
		setResident(resident);
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
	
	public void setResident(Resident resident)
	{
		this.resident = resident;
	}
	
	public Resident getResident()
	{
		return resident;
	}
}

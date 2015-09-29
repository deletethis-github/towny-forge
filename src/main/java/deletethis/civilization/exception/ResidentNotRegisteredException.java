package deletethis.civilization.exception;

import deletethis.civilization.Resident;
import deletethis.civilization.Town;

public class ResidentNotRegisteredException extends Exception
{
	private static final long serialVersionUID = 475022584109982537L;
	private Resident resident;
	private Town town;
	
	public ResidentNotRegisteredException(Resident resident, Town town)
	{
		setResident(resident);
		setTown(town);
	}
	
	public ResidentNotRegisteredException(String message)
	{
		super(message);
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

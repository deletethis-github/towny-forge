package deletethis.civilization.exception;

public class ResidentAlreadyInTownException extends CivilizationException
{
	private static final long serialVersionUID = -8936717534099035177L;

	public ResidentAlreadyInTownException()
	{
		super("Resident is already in this town.");
	}
	
	public ResidentAlreadyInTownException(String message)
	{
		super(message);
	}
}

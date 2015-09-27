package deletethis.civilization.exception;

public class ResidentNotInTownException extends CivilizationException
{
	private static final long serialVersionUID = 475022584109982537L;

	public ResidentNotInTownException()
	{
		super("Resident is not in this town.");
	}
	
	public ResidentNotInTownException(String message)
	{
		super(message);
	}
}

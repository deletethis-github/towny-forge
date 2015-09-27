package deletethis.civilization.exception;

public class TownDoesNotExistException extends CivilizationException
{
	private static final long serialVersionUID = -3218438269446275073L;

	public TownDoesNotExistException()
	{
		super("Town does not exist.");
	}
	
	public TownDoesNotExistException(String message)
	{
		super(message);
	}
}

package deletethis.civilization.exception;

public class TownAlreadyExistsException extends CivilizationException
{
	private static final long serialVersionUID = 4967435377063343813L;

	public TownAlreadyExistsException()
	{
		super("Town already exists.");
	}
	
	public TownAlreadyExistsException(String message)
	{
		super(message);
	}
}

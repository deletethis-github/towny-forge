package deletethis.civilization.exception;

public class CivilizationException extends Exception
{
	private static final long serialVersionUID = -4952165991122407290L;
	
	public CivilizationException()
	{
		super("unknown");
	}
	
	public CivilizationException(String message)
	{
		super(message);
	}
}

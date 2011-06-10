package monopoly.core.beans.event;

public interface IGameStart extends IEvent
{
	public static final String TYPE = "gamestart";

	public String toString();
}
package monopoly.core.beans.event;

import monopoly.core.beans.IPlayer;

public interface ICastDie extends IEvent
{
	public static final String TYPE = "castdie";

	public IPlayer getPlayer();

	public void setPlayer(IPlayer player);

	public String toString();
}
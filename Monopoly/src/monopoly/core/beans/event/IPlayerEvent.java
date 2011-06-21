package monopoly.core.beans.event;

import monopoly.core.beans.IPlayer;

public interface IPlayerEvent extends IEvent
{
	public IPlayer getPlayer();

	public void setPlayer(IPlayer player);

	public String toString();

}
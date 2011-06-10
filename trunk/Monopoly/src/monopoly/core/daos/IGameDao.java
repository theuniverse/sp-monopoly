package monopoly.core.daos;

import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;

public interface IGameDao
{
	public IHost createHost();

	public IHost getHostByKey(Long key);

	public IPlayer createPlayer();
}
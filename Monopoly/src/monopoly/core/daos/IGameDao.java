package monopoly.core.daos;

import monopoly.core.beans.IHost;

public interface IGameDao
{
	public IHost createHost();

	public IHost getHostByKey(Long key);
}
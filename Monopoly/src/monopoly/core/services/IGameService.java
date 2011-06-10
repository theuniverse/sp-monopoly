package monopoly.core.services;

import monopoly.core.beans.IHost;

public interface IGameService
{
	public IHost create(String username, String password);
}

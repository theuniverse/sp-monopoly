package monopoly.core.services;

import monopoly.core.beans.IHost;

public interface IGameService
{
	public IHost create(String username);

	public String join(String username, Long hostid);
}

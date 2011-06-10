package monopoly.core.services;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;

public interface IGameService
{
	public IHost create(String username);

	public String join(String username, Long hostid);

	public String start(String username, Long hostid);

	public boolean checkUser(String username, String password);

	public IGame initFetch(String username);
}

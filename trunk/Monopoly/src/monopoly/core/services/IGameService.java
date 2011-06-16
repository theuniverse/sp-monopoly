package monopoly.core.services;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;

public interface IGameService
{
	public IHost create(String username);

	public String join(String username, Long hostid);

	public String start(String username, Long hostid);

	public boolean checkUserAtInitStage(String username, String password);

	public boolean checkUser(String username, String password);

	public IGame initFetch(String username);

	public boolean checkCastDie(String username);

	public int castDie(String username);
}

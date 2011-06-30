package monopoly.core.services;

import java.util.ArrayList;
import java.util.List;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IUser;

public interface IGameService {
	public IHost create(String username);

	public String join(String username, Long hostid);

	public ArrayList<IHost> list(String username);

	public String start(String username, Long hostid);

	public boolean checkUserAtInitStage(String username, String password);

	public boolean checkUser(String username, String password);

	public IGame initFetch(String username);

	public int castDie(String username);

	public boolean buyProperty(String username);

	public boolean save(String username, Long amount);

	public boolean withdraw(String username, Long amount);

	public List<IUser> getUsersOfHost(Long hostid);
}

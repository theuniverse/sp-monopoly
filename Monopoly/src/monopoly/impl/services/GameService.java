package monopoly.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.daos.IEventDao;
import monopoly.core.daos.IGameDao;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IGameService;

public class GameService implements IGameService
{
	@Autowired
	private IGameDao gameDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IEventDao eventDao;

	public void setGameDao(IGameDao gameDao)
	{
		this.gameDao = gameDao;
	}

	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
	}

	public void setEventDao(IEventDao eventDao)
	{
		this.eventDao = eventDao;
	}

	@Transactional
	public IHost create(String username)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return null;

		IHost host = gameDao.createHost();
		host.getUsers().add(user);
		user.setHost(host);
		return host;
	}

	@Transactional
	public String join(String username, Long hostid)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return "Unexpected error";

		IHost host = gameDao.getHostByKey(hostid);
		if (host == null)
			return "Failed to find target host";

		if (host.getUsers().contains(user))
			return "User already in host";

		if (host.getUsers().size() >= 4)
			return "Host full";

		host.getUsers().add(user);
		user.setHost(host);
		return null;
	}

	@Transactional
	public String start(String username, Long hostid)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return "Unexpected error";

		IHost host = gameDao.getHostByKey(hostid);
		if (host == null)
			return "Failed to find target host";

		if (!user.equals(host.getUsers().get(0)))
			return "Only host can start game";

		IGame game = host.getGame();
		if (game == null)
			return "Unexpected error";

		if (host.isStarted())
			return "Game already started";
		host.setStarted(true);
		for (IUser iterator : host.getUsers())
		{
			IEventQueue eventQueue = eventDao.createEventQueue(iterator);
			eventDao.createEvent(eventQueue, IEventDao.INIT_FETCH_BEAN);
		}

		String colors[] =
		{ "RED", "BLUE", "YELLOW", "GREEN" };
		for (int i = 0; i < host.getUsers().size(); i++)
		{
			IUser iterator = host.getUsers().get(i);
			IPlayer player = gameDao.createPlayer(iterator, game);
			player.setCash(new Long(2000));
			player.setColor(colors[i]);

		}

		return null;
	}

}

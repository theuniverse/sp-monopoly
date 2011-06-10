package monopoly.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import monopoly.core.beans.IHost;
import monopoly.core.beans.IUser;
import monopoly.core.daos.IGameDao;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IGameService;

public class GameService implements IGameService
{
	@Autowired
	private IGameDao gameDao;

	@Autowired
	private IUserDao userDao;

	public void setGameDao(IGameDao gameDao)
	{
		this.gameDao = gameDao;
	}

	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
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

}

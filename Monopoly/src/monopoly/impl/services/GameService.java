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
	public IHost create(String username, String password)
	{
		IUser user = userDao.getUserByUsername(username);

		if (user == null)
			return null;

		IHost host = gameDao.createHost();
		host.getUsers().add(user);
		user.setHost(host);
		return host;
	}

}

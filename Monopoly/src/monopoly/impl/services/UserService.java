package monopoly.impl.services;

import monopoly.core.beans.IUser;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService
{
	@Autowired
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
	}

	public boolean register(String username, String password)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user != null)
			return false;

		userDao.createUser(username, password);
		return true;
	}

	public boolean login(String username, String password)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return false;

		if (user.getPassword().equals(password))
			return true;
		else
			return false;
	}

	public IUser auth(String username, String password)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return null;

		if (user.getPassword().equals(password))
			return user;
		else
			return null;
	}
}

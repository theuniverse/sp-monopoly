package monopoly.core.services;

import monopoly.core.beans.IUser;

public interface IUserService
{

	public boolean register(String username, String password);

	public boolean login(String username, String password);

	public IUser auth(String username, String password);
}
package monopoly.core.daos;

import monopoly.core.beans.IUser;

public interface IUserDao
{
	public static final String USER_BEAN = "user";

	public IUser createUser(String username, String password);

	public IUser getUserByUsername(String username);

}
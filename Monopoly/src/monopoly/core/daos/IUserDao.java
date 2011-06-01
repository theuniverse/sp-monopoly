package monopoly.core.daos;

import monopoly.core.beans.IUser;

public interface IUserDao {

	public IUser createUser(String username, String password);

	public IUser getUserByUsername(String username);

}
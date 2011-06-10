package monopoly.impl.daos;

import monopoly.core.beans.IUser;
import monopoly.core.daos.IUserDao;

import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.BaseDao;

@Transactional
public class UserDao extends BaseDao implements IUserDao
{
	public IUser createUser(String username, String password)
	{
		IUser user = (IUser) persistenceManager.newObject(USER_BEAN);
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

	public IUser getUserByUsername(String username)
	{
		return persistenceManager.getObject(USER_BEAN, "where username = '"
				+ username + "'");
	}
}

package monopoly.impl.daos;

import monopoly.core.beans.IUser;
import monopoly.core.daos.IUserDao;

import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.BaseDao;

@Transactional
public class UserDao extends BaseDao implements IUserDao {
	public static final String BEAN_NAME = "user";

	public IUser createUser(String username, String password) {
		IUser user = (IUser) persistenceManager.newObject(BEAN_NAME);
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

	public IUser getUserByUsername(String username) {
		return persistenceManager.getObject(BEAN_NAME, "where username = '"
				+ username + "'");
	}
}

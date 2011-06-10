package monopoly.core.beans;

import java.util.List;

import com.iiiss.spring.common.IBean;

public interface IHost extends IBean
{

	public IGame getGame();

	public void setGame(IGame game);

	public List<IUser> getUsers();

	public void setUsers(List<IUser> users);

	public boolean isStarted();

	public void setStarted(boolean isStarted);
}
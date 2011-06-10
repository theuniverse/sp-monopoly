package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IUser;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Host extends BaseBean implements IHost
{
	@OneToOne(targetEntity = Game.class)
	private IGame game;

	@OneToMany(targetEntity = User.class, mappedBy = "host")
	private List<IUser> users = new ArrayList<IUser>();

	@Basic
	boolean isStarted = false;

	public IGame getGame()
	{
		return game;
	}

	public void setGame(IGame game)
	{
		this.game = game;
	}

	public List<IUser> getUsers()
	{
		return users;
	}

	public void setUsers(List<IUser> users)
	{
		this.users = users;
	}

	public boolean isStarted()
	{
		return isStarted;
	}

	public void setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}

}

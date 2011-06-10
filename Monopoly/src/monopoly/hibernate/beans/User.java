package monopoly.hibernate.beans;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;

import com.iiiss.spring.common.BaseBean;

@Entity
public class User extends BaseBean implements IUser
{
	@Basic
	private String username;

	@Basic
	private String password;

	@ManyToOne(targetEntity = Host.class)
	private IHost host;

	@OneToOne(targetEntity = Player.class)
	private IPlayer player;

	@OneToOne(targetEntity = EventQueue.class)
	private IEventQueue eventQueue;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public IHost getHost()
	{
		return host;
	}

	public void setHost(IHost host)
	{
		this.host = host;
	}

	public IPlayer getPlayer()
	{
		return player;
	}

	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	public IEventQueue getEventQueue()
	{
		return eventQueue;
	}

	public void setEventQueue(IEventQueue eventQueue)
	{
		this.eventQueue = eventQueue;
	}

}

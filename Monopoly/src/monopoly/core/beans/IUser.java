package monopoly.core.beans;

import com.iiiss.spring.common.IBean;

public interface IUser extends IBean
{
	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public IHost getHost();

	public void setHost(IHost host);

	public IPlayer getPlayer();

	public void setPlayer(IPlayer player);

	public IEventQueue getEventQueue();

	public void setEventQueue(IEventQueue eventQueue);
}
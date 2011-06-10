package monopoly.core.services;

import java.util.List;

import monopoly.core.beans.event.IEvent;
import monopoly.core.daos.IUserDao;

public interface IEventService
{
	public void setUserDao(IUserDao userDao);

	public List<IEvent> fetch(String username, String password);

}
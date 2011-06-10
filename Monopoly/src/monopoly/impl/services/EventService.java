package monopoly.impl.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import monopoly.core.beans.IUser;
import monopoly.core.beans.event.IEvent;
import monopoly.core.daos.IEventDao;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IEventService;

public class EventService implements IEventService
{
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IEventDao eventDao;

	public void setUserDao(IUserDao userDao)
	{
		this.userDao = userDao;
	}

	public void setEventDao(IEventDao eventDao)
	{
		this.eventDao = eventDao;
	}

	@Transactional
	public List<IEvent> fetch(String username, String password)
	{
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return null;

		if (user.getEventQueue() == null)
			return null;

		List<IEvent> clone = new ArrayList<IEvent>();
		clone.addAll(user.getEventQueue().getEvents());
		eventDao.createEventQueue(user);

		return clone;
	}
}

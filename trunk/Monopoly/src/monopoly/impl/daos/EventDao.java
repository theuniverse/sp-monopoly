package monopoly.impl.daos;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.IEvent;
import monopoly.core.daos.IEventDao;

import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.BaseDao;

@Transactional
public class EventDao extends BaseDao implements IEventDao
{

	public IEventQueue createEventQueue(IUser user)
	{
		IEventQueue eventQueue = (IEventQueue) persistenceManager
				.newObject(EVENT_QUEUE_BEAN);
		user.setEventQueue(eventQueue);
		eventQueue.setUser(user);
		return eventQueue;
	}

	public IEvent createEvent(IEventQueue eventQueue, String clazz)
	{
		IEvent event = (IEvent) persistenceManager.newObject(clazz);
		event.setEventQueue(eventQueue);
		eventQueue.getEvents().add(event);
		return event;
	}

	public void emptyEventQueue(IEventQueue eventQueue)
	{
		eventQueue.getEvents().clear();
	}

}

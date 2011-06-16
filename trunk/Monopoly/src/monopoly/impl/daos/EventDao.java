package monopoly.impl.daos;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.ICastDie;
import monopoly.core.beans.event.IEvent;
import monopoly.core.beans.event.IStepForward;
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

	private ICastDie createCastDieEvent(IEventQueue eventQueue, IPlayer player)
	{
		ICastDie event = (ICastDie) persistenceManager.newObject(CAST_DIE_BEAN);
		event.setEventQueue(eventQueue);
		eventQueue.getEvents().add(event);
		event.setPlayer(player);
		return event;
	}

	public ICastDie createCastDieEvent(IGame game, IPlayer player)
	{
		ICastDie event = (ICastDie) persistenceManager.newObject(CAST_DIE_BEAN);
		event.setPlayer(player);
		game.getEvents().add(event);
		for (IPlayer p : game.getPlayers())
			createCastDieEvent(p.getUser().getEventQueue(), player);
		return event;
	}

	public IStepForward createStepForwardEvent(IEventQueue eventQueue,
			IPlayer player, int step)
	{
		IStepForward event = (IStepForward) persistenceManager
				.newObject(STEP_FORWARD_BEAN);
		event.setPlayer(player);
		event.setStep(step);
		event.setEventQueue(eventQueue);
		eventQueue.getEvents().add(event);
		return event;
	}

}

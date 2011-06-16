package monopoly.core.daos;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.ICastDie;
import monopoly.core.beans.event.IEvent;
import monopoly.core.beans.event.IStepForward;

public interface IEventDao
{
	public static final String EVENT_BEAN = "event";
	public static final String EVENT_QUEUE_BEAN = "eventQueue";
	public static final String INIT_FETCH_BEAN = "initFetch";
	public static final String GAME_START_BEAN = "gameStart";
	public static final String CAST_DIE_BEAN = "castDie";
	public static final String STEP_FORWARD_BEAN = "stepForward";

	public IEventQueue createEventQueue(IUser user);

	public IEvent createEvent(IEventQueue eventQueue, String clazz);

	public ICastDie createCastDieEvent(IGame game, IPlayer player);

	public IStepForward createStepForwardEvent(IEventQueue eventQueue,
			IPlayer player, int step);

}
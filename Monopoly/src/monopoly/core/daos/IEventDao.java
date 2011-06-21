package monopoly.core.daos;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.IBuyProperty;
import monopoly.core.beans.event.ICashBonusPassingStart;
import monopoly.core.beans.event.ICastDie;
import monopoly.core.beans.event.IGameStart;
import monopoly.core.beans.event.IInitFetch;
import monopoly.core.beans.event.IStepForward;

public interface IEventDao
{
	public static final String EVENT_BEAN = "event";
	public static final String EVENT_QUEUE_BEAN = "eventQueue";
	public static final String INIT_FETCH_BEAN = "initFetch";
	public static final String GAME_START_BEAN = "gameStart";
	public static final String CAST_DIE_BEAN = "castDie";
	public static final String STEP_FORWARD_BEAN = "stepForward";
	public static final String CASH_BONUS_PASSING_START_BEAN = "cashBonusPassingStart";
	public static final String BUY_PROPERTY_BEAN = "buyProperty";

	public IEventQueue createEventQueue(IUser user);

	public IInitFetch createInitFetchEvent(IGame game);

	public IGameStart createGameStartEvent(IGame game);

	public ICastDie createCastDieEvent(IGame game, IPlayer player);

	public IStepForward createStepForwardEvent(IGame game, IPlayer player,
			int step);

	public ICashBonusPassingStart createCashBonusPassingStartEvent(IGame game,
			IPlayer player, int cash);

	public IBuyProperty createBuyPropertyEvent(IGame game, IPlayer player,
			int field, int cost);
}
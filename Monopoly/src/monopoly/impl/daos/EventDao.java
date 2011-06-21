package monopoly.impl.daos;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.IBankServiceAsk;
import monopoly.core.beans.event.IBankServiceSave;
import monopoly.core.beans.event.IBankServiceWithdraw;
import monopoly.core.beans.event.IBuyProperty;
import monopoly.core.beans.event.IBuyPropertyAsk;
import monopoly.core.beans.event.ICashBonusPassingStart;
import monopoly.core.beans.event.ICastDie;
import monopoly.core.beans.event.IEvent;
import monopoly.core.beans.event.IGameStart;
import monopoly.core.beans.event.IInitFetch;
import monopoly.core.beans.event.IPayTax;
import monopoly.core.beans.event.IStepForward;
import monopoly.core.beans.field.IField;
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

	private void pushTo(IEvent event, IEventQueue eventQueue)
	{
		eventQueue.getEvents().add(event);
	}

	public IInitFetch createInitFetchEvent(IGame game)
	{
		IInitFetch event = (IInitFetch) persistenceManager
				.newObject(INIT_FETCH_BEAN);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IGameStart createGameStartEvent(IGame game)
	{
		IGameStart event = (IGameStart) persistenceManager
				.newObject(GAME_START_BEAN);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public ICastDie createCastDieEvent(IGame game, IPlayer player)
	{
		ICastDie event = (ICastDie) persistenceManager.newObject(CAST_DIE_BEAN);
		event.setPlayer(player);
		game.getEvents().add(event);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IStepForward createStepForwardEvent(IGame game, IPlayer player,
			int step)
	{
		IStepForward event = (IStepForward) persistenceManager
				.newObject(STEP_FORWARD_BEAN);
		event.setPlayer(player);
		event.setStep(step);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public ICashBonusPassingStart createCashBonusPassingStartEvent(IGame game,
			IPlayer player, int cash)
	{
		ICashBonusPassingStart event = (ICashBonusPassingStart) persistenceManager
				.newObject(CASH_BONUS_PASSING_START_BEAN);
		event.setPlayer(player);
		event.setCash(cash);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IBuyPropertyAsk createBuyPropertyAskEvent(IGame game,
			IPlayer player, IField field, Long cost)
	{
		IBuyPropertyAsk event = (IBuyPropertyAsk) persistenceManager
				.newObject(BUY_PROPERTY_ASK_BEAN);
		event.setCost(cost);
		event.setField(field.getPosition());
		event.setPlayer(player);
		game.getEvents().add(event);
		pushTo(event, player.getUser().getEventQueue());
		return event;
	}

	public IBuyProperty createBuyPropertEvent(IGame game, IPlayer player,
			IField field, Long cost)
	{
		IBuyProperty event = (IBuyProperty) persistenceManager
				.newObject(BUY_PROPERTY_BEAN);
		event.setCost(cost);
		event.setField(field.getPosition());
		event.setPlayer(player);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IPayTax createPayTaxEvent(IGame game, IPlayer player,
			IPlayer landlord, Long tax)
	{
		IPayTax event = (IPayTax) persistenceManager.newObject(PAY_TAX_BEAN);
		event.setTax(tax);
		event.setLandlord(landlord);
		event.setPlayer(player);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IBankServiceAsk createBankServiceAskEvent(IGame game, IPlayer player)
	{
		IBankServiceAsk event = (IBankServiceAsk) persistenceManager
				.newObject(BANK_SERVICE_ASK_BEAN);
		event.setPlayer(player);
		game.getEvents().add(event);
		pushTo(event, player.getUser().getEventQueue());
		return event;
	}

	public IBankServiceSave createBankServiceSaveEvent(IGame game,
			IPlayer player, Long amount)
	{
		IBankServiceSave event = (IBankServiceSave) persistenceManager
				.newObject(BANK_SERVICE_SAVE_BEAN);
		event.setAmount(amount);
		event.setPlayer(player);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}

	public IBankServiceWithdraw createBankServiceWithdrawEvent(IGame game,
			IPlayer player, Long amount)
	{
		IBankServiceWithdraw event = (IBankServiceWithdraw) persistenceManager
				.newObject(BANK_SERVICE_WITHDRAW_BEAN);
		event.setAmount(amount);
		event.setPlayer(player);
		for (IUser user : game.getHost().getUsers())
			pushTo(event, user.getEventQueue());
		return event;
	}
}

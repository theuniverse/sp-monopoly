package monopoly.core.daos;

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
import monopoly.core.beans.event.IGameStart;
import monopoly.core.beans.event.IInitFetch;
import monopoly.core.beans.event.IPayTax;
import monopoly.core.beans.event.IStepForward;
import monopoly.core.beans.field.IField;

public interface IEventDao
{
	public static final String EVENT_BEAN = "event";
	public static final String EVENT_QUEUE_BEAN = "eventQueue";
	public static final String INIT_FETCH_BEAN = "initFetch";
	public static final String GAME_START_BEAN = "gameStart";
	public static final String CAST_DIE_BEAN = "castDie";
	public static final String STEP_FORWARD_BEAN = "stepForward";
	public static final String CASH_BONUS_PASSING_START_BEAN = "cashBonusPassingStart";
	public static final String BUY_PROPERTY_ASK_BEAN = "buyPropertyAsk";
	public static final String BUY_PROPERTY_BEAN = "buyProperty";
	public static final String PAY_TAX_BEAN = "payTax";
	public static final String BANK_SERVICE_ASK_BEAN = "bankServiceAsk";
	public static final String BANK_SERVICE_SAVE_BEAN = "bankServiceSave";
	public static final String BANK_SERVICE_WITHDRAW_BEAN = "bankServiceWithdraw";

	public IEventQueue createEventQueue(IUser user);

	public IInitFetch createInitFetchEvent(IGame game);

	public IGameStart createGameStartEvent(IGame game);

	public ICastDie createCastDieEvent(IGame game, IPlayer player);

	public IStepForward createStepForwardEvent(IGame game, IPlayer player,
			int step);

	public ICashBonusPassingStart createCashBonusPassingStartEvent(IGame game,
			IPlayer player, int cash);

	public IBuyPropertyAsk createBuyPropertyAskEvent(IGame game,
			IPlayer player, IField field, Long cost);

	public IBuyProperty createBuyPropertEvent(IGame game, IPlayer player,
			IField field, Long cost);

	public IPayTax createPayTaxEvent(IGame game, IPlayer player,
			IPlayer landlord, Long tax);

	public IBankServiceAsk createBankServiceAskEvent(IGame game, IPlayer player);

	public IBankServiceSave createBankServiceSaveEvent(IGame game,
			IPlayer player, Long amount);

	public IBankServiceWithdraw createBankServiceWithdrawEvent(IGame game,
			IPlayer player, Long amount);
}
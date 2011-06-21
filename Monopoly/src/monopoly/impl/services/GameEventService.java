package monopoly.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.event.IBankServiceAsk;
import monopoly.core.beans.event.IBuyPropertyAsk;
import monopoly.core.beans.event.ICastDie;
import monopoly.core.beans.event.IEvent;
import monopoly.core.daos.IEventDao;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IGameEventService;

public class GameEventService implements IGameEventService
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
	public boolean checkCastDie(String username)
	{
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();
		if (game.getEvents().size() == 0)
			return false;
		IEvent event = game.getEvents().get(0);
		if (event instanceof ICastDie)
			if (((ICastDie) event).getPlayer().getUser().getUsername()
					.equals(username))
			{
				game.getEvents().remove(event);
				return true;
			}
		return false;
	}

	@Transactional
	public void checkNextPlayer(String username)
	{
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();
		if (game.getEvents().size() == 0)
		{
			int next = game.getPlayers().indexOf(player) + 1;
			if (next >= game.getPlayers().size())
				next = 0;
			IPlayer nextPlayer = game.getPlayers().get(next);
			game.setCurrentPlayer(nextPlayer);
			eventDao.createCastDieEvent(game, nextPlayer);
		}
	}

	@Transactional
	public boolean checkBuyProperty(String username)
	{
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();
		if (game.getEvents().size() == 0)
			return false;
		IEvent event = game.getEvents().get(0);
		if (event instanceof IBuyPropertyAsk)
			if (((IBuyPropertyAsk) event).getPlayer().getUser().getUsername()
					.equals(username))
			{
				game.getEvents().remove(event);
				return true;
			}
		return false;
	}

	@Transactional
	public boolean checkBankService(String username)
	{
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();
		if (game.getEvents().size() == 0)
			return false;
		IEvent event = game.getEvents().get(0);
		if (event instanceof IBankServiceAsk)
			if (((IBankServiceAsk) event).getPlayer().getUser().getUsername()
					.equals(username))
			{
				game.getEvents().remove(event);
				return true;
			}
		return false;

	}
}

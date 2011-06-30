package monopoly.impl.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IProperty;
import monopoly.core.beans.IUser;
import monopoly.core.beans.field.IBankField;
import monopoly.core.beans.field.IChanceField;
import monopoly.core.beans.field.IField;
import monopoly.core.beans.field.IJailField;
import monopoly.core.beans.field.INewsField;
import monopoly.core.beans.field.INormalField;
import monopoly.core.beans.field.IRailwayStationField;
import monopoly.core.daos.IEventDao;
import monopoly.core.daos.IGameDao;
import monopoly.core.daos.IUserDao;
import monopoly.core.services.IGameService;

public class GameService implements IGameService {
	@Autowired
	private IGameDao gameDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IEventDao eventDao;

	public void setGameDao(IGameDao gameDao) {
		this.gameDao = gameDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setEventDao(IEventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Transactional
	public void initInfo(String username, List<IPlayer>  players, List<IField> fields){
		IUser user = (IUser) userDao.getUserByUsername(username);
		
		IGame game = user.getPlayer().getGame();
		
		players.addAll(game.getPlayers());
		
		fields.addAll(game.getMap().getFields());
	}

	@Transactional
	public IHost create(String username) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return null;

		IHost host = gameDao.createHost();
		host.getUsers().add(user);
		user.setHost(host);
		return host;
	}

	@Transactional
	public ArrayList<IHost> list(String username) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return null;

		ArrayList<IHost> hosts = new ArrayList<IHost>();
		hosts = gameDao.getHosts();
		return hosts;
	}

	@Transactional
	public String join(String username, Long hostid) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return "Unexpected error";

		IHost host = gameDao.getHostByKey(hostid);
		if (host == null)
			return "Failed to find target host";

		if (host.getUsers().contains(user))
			return "User already in host";

		if (host.getUsers().size() >= 4)
			return "Host full";

		host.getUsers().add(user);
		user.setHost(host);
		return null;
	}

	@Transactional
	public String start(String username, Long hostid) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return "Unexpected error";

		IHost host = gameDao.getHostByKey(hostid);
		if (host == null)
			return "Failed to find target host";

		if (!user.equals(host.getUsers().get(0)))
			return "Only host can start game";

		IGame game = host.getGame();
		if (game == null)
			return "Unexpected error";

		if (host.isStarted())
			return "Game already started";
		host.setStarted(true);
		for (IUser iterator : host.getUsers())
			eventDao.createEventQueue(iterator);
		eventDao.createInitFetchEvent(game);

		String colors[] = { "RED", "BLUE", "YELLOW", "GREEN" };
		for (int i = 0; i < host.getUsers().size(); i++) {
			IUser iterator = host.getUsers().get(i);
			IPlayer player = gameDao.createPlayer(iterator, game);
			player.setCash(new Long(2000));
			player.setColor(colors[i]);
		}
		game.setCashBonusPassingStart(200);

		gameDao.createMap(game);

		return null;
	}

	@Transactional
	public boolean checkUserAtInitStage(String username, String password) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return false;

		if (user.getHost() == null)
			return false;

		IHost host = user.getHost();
		if (host == null)
			return false;

		if (!host.isStarted())
			return false;

		if (host.getGame() == null)
			return false;

		if (host.getGame().isStarted())
			return false;

		for (IUser iterator : host.getUsers())
			if (user == iterator)
				return true;

		return false;
	}

	@Transactional
	public boolean checkUser(String username, String password) {
		IUser user = userDao.getUserByUsername(username);
		if (user == null)
			return false;

		if (user.getHost() == null)
			return false;

		IHost host = user.getHost();
		if (host == null)
			return false;

		if (!host.isStarted())
			return false;

		if (host.getGame() == null)
			return false;

		if (!host.getGame().isStarted())
			return false;

		for (IUser iterator : host.getUsers())
			if (user == iterator)
				return true;

		return false;
	}

	@Transactional
	public IGame initFetch(String username) {
		IUser user = userDao.getUserByUsername(username);
		IHost host = user.getHost();
		IGame game = host.getGame();
		IPlayer player = user.getPlayer();

		player.setInitialized(true);
		boolean allReady = true;
		for (IPlayer p : game.getPlayers())
			if (!p.isInitialized())
				allReady = false;

		if (allReady) {
			game.setStarted(true);
			eventDao.createGameStartEvent(game);
			eventDao.createCastDieEvent(game, game.getPlayers().get(0));
			game.setCurrentPlayer(game.getPlayers().get(0));
		}

		return game;
	}

	@Transactional
	public int castDie(String username) {
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();

		int step = (int) (Math.random() * 6) + 1;
		player.getField().getPlayers().remove(player);
		IField field = player.getField();
		for (int i = 0; i < step; i++) {
			field = field.getNext();
			/* see if the player passes the start point */
			if (field == game.getMap().getStartField()) {
				player.setCash(player.getCash()
						+ game.getCashBonusPassingStart());
				eventDao.createCashBonusPassingStartEvent(game, player, game
						.getCashBonusPassingStart());
			}
			/* see if the player passes a bank */
			if (field instanceof IBankField) {
				eventDao.createBankServiceAskEvent(game, player);
			}
		}
		field.getPlayers().add(player);
		player.setField(field);

		eventDao.createStepForwardEvent(game, player, step);

		/* see if it's gonna happen something */
		if (field instanceof INormalField) {
			INormalField normalField = (INormalField) field;
			if (normalField.getProperty().getPlayer() == null) {
				eventDao.createBuyPropertyAskEvent(game, player, normalField,
						normalField.getProperty().getValue());
			} else {
				Long tax = Math.min(player.getCash(), (long) (normalField
						.getProperty().getValue() * 0.2));
				IPlayer landlord = normalField.getProperty().getPlayer();
				if (landlord != player) {
					if (player.getCash() >= normalField.getProperty()
							.getValue()) {
						player.setCash(player.getCash() - tax);
						landlord.setCash(landlord.getCash() + tax);
					}
					eventDao.createPayTaxEvent(game, player, landlord, tax);
				} else {
					// upgrade property
					eventDao.createBuyPropertyAskEvent(game, player, normalField, normalField.getProperty().getValue());			
				}
			}
		}else if(field instanceof IRailwayStationField){
			
		}else if(field instanceof IJailField){
			
		}else if(field instanceof INewsField){
			
		}else if(field instanceof IChanceField){
			
		}

		return step;
	}

	@Transactional
	public boolean buyProperty(String username) {
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();

		if (!(player.getField() instanceof INormalField)) {
			System.out.println("Buy Property Failed. Not buyable field");
			return false;
		}

		IProperty property = ((INormalField) player.getField()).getProperty();
		property.setPlayer(player);

		if (player.getCash() < property.getValue()) {
			System.out.println("Buy Property Failed. Not enough cash");
			return false;
		}
		player.setCash(player.getCash() - property.getValue());

		eventDao.createBuyPropertEvent(game, player, player.getField(),
				property.getValue());

		return true;
	}

	@Transactional
	public boolean save(String username, Long amount) {
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();

		if (player.getCash() < amount) {
			System.out.println("Save Failed. Not enough cash");
			return false;
		}
		player.setCash(player.getCash() - amount);
		player.setSaving(player.getSaving() + amount);

		eventDao.createBankServiceSaveEvent(game, player, amount);

		return true;
	}

	@Transactional
	public boolean withdraw(String username, Long amount) {
		IPlayer player = userDao.getUserByUsername(username).getPlayer();
		IGame game = player.getGame();

		if (player.getSaving() < amount) {
			System.out.println("Save Failed. Not enough saving");
			return false;
		}
		player.setCash(player.getCash() + amount);
		player.setSaving(player.getSaving() - amount);

		eventDao.createBankServiceWithdrawEvent(game, player, amount);

		return true;
	}

	@Transactional
	public List<IUser> getUsersOfHost(Long hostid) {
		List<IUser> users = new ArrayList<IUser>();
		List<IHost> hosts = gameDao.getHosts();
		for (IHost host : hosts)
			if (host.getKey() == hostid) {
				users.addAll(host.getUsers());
				return users;
			}
		return Collections.emptyList();
	}
}

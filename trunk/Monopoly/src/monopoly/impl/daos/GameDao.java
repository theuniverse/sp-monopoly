package monopoly.impl.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.BaseDao;

import monopoly.core.beans.IField;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IMap;
import monopoly.core.beans.INormalField;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IProperty;
import monopoly.core.beans.IStartField;
import monopoly.core.beans.IUser;
import monopoly.core.daos.IGameDao;

@Transactional
public class GameDao extends BaseDao implements IGameDao
{
	private IGame createGame()
	{
		IGame game = (IGame) persistenceManager.newObject(GAME_BEAN);
		return game;
	}

	public IHost createHost()
	{
		IHost host = (IHost) persistenceManager.newObject(HOST_BEAN);
		IGame game = createGame();
		host.setGame(game);
		game.setHost(host);
		return host;
	}

	public IPlayer createPlayer(IUser user, IGame game)
	{
		IPlayer player = (IPlayer) persistenceManager.newObject(PLAYER_BEAN);
		user.setPlayer(player);
		player.setUser(user);
		game.getPlayers().add(player);
		player.setGame(game);
		return player;
	}

	public IHost getHostByKey(Long key)
	{
		IHost host = (IHost) persistenceManager.getObject(HOST_BEAN, key);
		return host;
	}
	
	public ArrayList<IHost> getHosts()
	{
		List<IHost> tempHosts = persistenceManager.getObjects(HOST_BEAN, "");
		ArrayList<IHost> hosts = new ArrayList<IHost>();
		if(tempHosts != null){
			for(int i = 0;i< tempHosts.size();i++){
				hosts.add(tempHosts.get(i));
			}
		}
		return hosts;
	}

	private void push(IField field, IMap map)
	{
		field.setPosition(map.getStartField().getPrevious().getPosition() + 1);
		map.getFields().add(field);
		field.setMap(map);

		field.setNext(map.getStartField());
		field.setPrevious(map.getStartField().getPrevious());
		map.getStartField().getPrevious().setNext(field);
		map.getStartField().setPrevious(field);
	}

	private INormalField createNormalField()
	{
		INormalField normalField = (INormalField) persistenceManager
				.newObject(NORMAL_FIELD_BEAN);
		IProperty property = (IProperty) persistenceManager
				.newObject(PROPERTY_BEAN);
		normalField.setProperty(property);
		property.setField(normalField);
		return normalField;
	}

	public IMap createMap(IGame game)
	{
		IMap map = (IMap) persistenceManager.newObject(MAP_BEAN);
		game.setMap(map);
		map.setGame(game);

		IStartField startField = (IStartField) persistenceManager
				.newObject(START_FIELD_BEAN);
		startField.setPosition(0);
		startField.setNext(startField);
		startField.setPrevious(startField);
		startField.setMap(map);
		map.setStartField(startField);
		map.getFields().add(startField);

		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);
		push(createNormalField(), map);

		map.getStartField().getPlayers().addAll(game.getPlayers());
		for (IPlayer player : game.getPlayers())
			player.setField(map.getStartField());

		return map;
	}

}

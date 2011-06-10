package monopoly.impl.daos;

import org.springframework.transaction.annotation.Transactional;

import com.iiiss.spring.common.BaseDao;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;
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

}

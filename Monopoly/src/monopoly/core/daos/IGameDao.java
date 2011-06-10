package monopoly.core.daos;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;

public interface IGameDao
{
	public static final String GAME_BEAN = "game";
	public static final String HOST_BEAN = "host";
	public static final String PLAYER_BEAN = "player";

	public IHost createHost();

	public IHost getHostByKey(Long key);

	public IPlayer createPlayer(IUser user, IGame game);
}
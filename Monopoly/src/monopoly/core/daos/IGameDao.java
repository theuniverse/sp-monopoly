package monopoly.core.daos;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IMap;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;

public interface IGameDao
{
	public static final String GAME_BEAN = "game";
	public static final String HOST_BEAN = "host";
	public static final String PLAYER_BEAN = "player";
	public static final String MAP_BEAN = "map";
	public static final String FIELD_BEAN = "field";
	public static final String PROPERTY_BEAN = "property";
	public static final String START_FIELD_BEAN = "startField";
	public static final String NORMAL_FIELD_BEAN = "normalField";
	public static final String PARK_FIELD_BEAN = "parkField";
	public static final String BANK_FIELD_BEAN = "bankField";
	public static final String JAIL_FIELD_BEAN = "jailField";
	public static final String NEWS_FIELD_BEAN = "newsField";

	public IHost createHost();

	public IHost getHostByKey(Long key);

	public IMap createMap(IGame game);

	public IPlayer createPlayer(IUser user, IGame game);
}
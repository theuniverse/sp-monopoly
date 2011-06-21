package monopoly.core.beans;

import java.util.List;

import monopoly.core.beans.event.IEvent;

import com.iiiss.spring.common.IBean;

public interface IGame extends IBean
{
	public boolean isStarted();

	public void setStarted(boolean isStarted);

	public IHost getHost();

	public void setHost(IHost host);

	public List<IPlayer> getPlayers();

	public void setPlayers(List<IPlayer> players);

	public IMap getMap();

	public void setMap(IMap map);

	public List<IEvent> getEvents();

	public void setEvents(List<IEvent> events);

	public void setCashBonusPassingStart(int cashBonusPassingStart);

	public int getCashBonusPassingStart();

	public void setCurrentPlayer(IPlayer currentPlayer);

	public IPlayer getCurrentPlayer();

}
package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;
import monopoly.core.beans.IPlayer;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Game extends BaseBean implements IGame
{
	@Basic
	private boolean isStarted = false;

	@OneToOne(targetEntity = Host.class, cascade =
	{ CascadeType.ALL })
	private IHost host;

	@OneToMany(targetEntity = Player.class, mappedBy = "game")
	private List<IPlayer> players = new ArrayList<IPlayer>();

	@OneToOne(targetEntity = Player.class)
	private IPlayer currentPlayer;

	public boolean isStarted()
	{
		return isStarted;
	}

	public void setStarted(boolean isStarted)
	{
		this.isStarted = isStarted;
	}

	public IHost getHost()
	{
		return host;
	}

	public void setHost(IHost host)
	{
		this.host = host;
	}

	public List<IPlayer> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<IPlayer> players)
	{
		this.players = players;
	}

	public IPlayer getCurrentPlayer()
	{
		return currentPlayer;
	}

	public void setCurrentPlayer(IPlayer currentPlayer)
	{
		this.currentPlayer = currentPlayer;
	}

}

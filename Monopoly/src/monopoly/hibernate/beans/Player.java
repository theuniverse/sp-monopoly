package monopoly.hibernate.beans;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Player extends BaseBean implements IPlayer
{
	@Basic
	private String color;

	@Basic
	private Long cash;

	@Basic
	private boolean isInitialized = false;

	@ManyToOne(targetEntity = Game.class, cascade =
	{ CascadeType.ALL })
	private IGame game;

	@OneToOne(targetEntity = User.class, cascade =
	{ CascadeType.ALL })
	private IUser user;

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public Long getCash()
	{
		return cash;
	}

	public void setCash(Long cash)
	{
		this.cash = cash;
	}

	public IGame getGame()
	{
		return game;
	}

	public void setGame(IGame game)
	{
		this.game = game;
	}

	public IUser getUser()
	{
		return user;
	}

	public void setUser(IUser user)
	{
		this.user = user;
	}

	public boolean isInitialized()
	{
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized)
	{
		this.isInitialized = isInitialized;
	}

}

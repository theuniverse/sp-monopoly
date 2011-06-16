package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IField;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IProperty;
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
	private Long saving;

	@Basic
	private boolean isInitialized = false;

	@ManyToOne(targetEntity = Game.class, cascade =
	{ CascadeType.ALL })
	private IGame game;

	@OneToOne(targetEntity = User.class, cascade =
	{ CascadeType.ALL })
	private IUser user;

	@OneToMany(targetEntity = Property.class, mappedBy = "player")
	private List<IProperty> properties = new ArrayList<IProperty>();

	@ManyToOne(targetEntity = Field.class)
	private IField field;

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

	public Long getSaving()
	{
		return saving;
	}

	public void setSaving(Long saving)
	{
		this.saving = saving;
	}

	public boolean isInitialized()
	{
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized)
	{
		this.isInitialized = isInitialized;
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

	public List<IProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(List<IProperty> properties)
	{
		this.properties = properties;
	}

	public IField getField()
	{
		return field;
	}

	public void setField(IField field)
	{
		this.field = field;
	}

}

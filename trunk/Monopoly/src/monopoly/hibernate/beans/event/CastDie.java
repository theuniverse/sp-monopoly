package monopoly.hibernate.beans.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import monopoly.core.beans.IPlayer;
import monopoly.core.beans.event.ICastDie;
import monopoly.hibernate.beans.Player;

@Entity
@DiscriminatorValue("CastDie")
public class CastDie extends Event implements ICastDie
{
	@ManyToOne(targetEntity = Player.class)
	private IPlayer player;

	{
		this.setType(TYPE);
	}

	public IPlayer getPlayer()
	{
		return player;
	}

	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + player.getColor() + "'";
	}
}

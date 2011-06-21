package monopoly.hibernate.beans.event;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import monopoly.core.beans.IPlayer;
import monopoly.core.beans.event.IPlayerEvent;
import monopoly.hibernate.beans.Player;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class PlayerEvent extends Event implements IPlayerEvent
{
	@ManyToOne(targetEntity = Player.class)
	private IPlayer player;

	public IPlayer getPlayer()
	{
		return player;
	}

	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	public abstract String toString();
}

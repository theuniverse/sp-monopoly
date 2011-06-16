package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import monopoly.core.beans.IPlayer;
import monopoly.core.beans.event.IStepForward;
import monopoly.hibernate.beans.Player;

@Entity
@DiscriminatorValue("StepForward")
public class StepForward extends Event implements IStepForward
{
	@ManyToOne(targetEntity = Player.class)
	private IPlayer player;

	@Basic
	private int step;

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

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + player.getColor()
				+ "';'step:'" + step + "'";
	}
}

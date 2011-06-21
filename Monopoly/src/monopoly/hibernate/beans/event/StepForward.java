package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import monopoly.core.beans.event.IStepForward;

@Entity
@DiscriminatorValue("StepForward")
public class StepForward extends PlayerEvent implements IStepForward
{
	@Basic
	private int step;

	{
		this.setType(TYPE);
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
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "';'step:'" + step + "'";
	}
}

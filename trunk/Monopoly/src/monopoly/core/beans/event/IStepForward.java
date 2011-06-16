package monopoly.core.beans.event;

import monopoly.core.beans.IPlayer;

public interface IStepForward extends IEvent
{
	public static final String TYPE = "stepforward";

	public IPlayer getPlayer();

	public void setPlayer(IPlayer player);

	public int getStep();

	public void setStep(int step);

	public String toString();
}
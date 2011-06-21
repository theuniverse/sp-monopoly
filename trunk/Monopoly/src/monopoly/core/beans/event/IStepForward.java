package monopoly.core.beans.event;

public interface IStepForward extends IPlayerEvent
{
	public static final String TYPE = "stepforward";

	public int getStep();

	public void setStep(int step);

	public String toString();
}
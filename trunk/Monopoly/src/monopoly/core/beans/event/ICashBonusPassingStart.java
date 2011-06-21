package monopoly.core.beans.event;

public interface ICashBonusPassingStart extends IPlayerEvent
{
	public static final String TYPE = "cashbonuspassingstart";

	public int getCash();

	public void setCash(int cash);

	public String toString();

}
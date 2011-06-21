package monopoly.core.beans.event;

public interface IBuyPropertyAsk extends IPlayerEvent
{
	public static final String TYPE = "buypropertyask";

	public int getField();

	public void setField(int field);

	public Long getCost();

	public void setCost(Long cost);

	public String toString();

}
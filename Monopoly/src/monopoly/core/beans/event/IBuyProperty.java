package monopoly.core.beans.event;

public interface IBuyProperty extends IPlayerEvent
{
	public static final String TYPE = "buyproperty";

	public int getField();

	public void setField(int field);

	public Long getCost();

	public void setCost(Long cost);

	public String toString();

}
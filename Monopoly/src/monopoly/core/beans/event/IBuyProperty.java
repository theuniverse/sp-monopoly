package monopoly.core.beans.event;

public interface IBuyProperty
{
	public static final String TYPE = "buyproperty";

	public int getField();

	public void setField(int field);

	public int getCost();

	public void setCost(int cost);

	public String toString();

}
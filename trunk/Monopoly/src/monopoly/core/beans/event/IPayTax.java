package monopoly.core.beans.event;

import monopoly.core.beans.IPlayer;

public interface IPayTax extends IPlayerEvent
{
	public static final String TYPE = "payTax";

	public IPlayer getLandlord();

	public void setLandlord(IPlayer landlord);

	public Long getTax();

	public void setTax(Long tax);

	public String toString();

}
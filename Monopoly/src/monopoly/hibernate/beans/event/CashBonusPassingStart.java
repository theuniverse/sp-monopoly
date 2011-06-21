package monopoly.hibernate.beans.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.ICashBonusPassingStart;

@Entity
@DiscriminatorValue("CashBonusPassingStart")
public class CashBonusPassingStart extends PlayerEvent implements
		ICashBonusPassingStart
{
	private int cash;

	{
		this.setType(TYPE);
	}

	public int getCash()
	{
		return cash;
	}

	public void setCash(int cash)
	{
		this.cash = cash;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "';'cash:'" + cash + "'";
	}
}

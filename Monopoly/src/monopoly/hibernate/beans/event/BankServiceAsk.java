package monopoly.hibernate.beans.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.IBankServiceAsk;

@Entity
@DiscriminatorValue("BankServiceAsk")
public class BankServiceAsk extends PlayerEvent implements IBankServiceAsk
{
	{
		this.setType(TYPE);
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "'";
	}

}

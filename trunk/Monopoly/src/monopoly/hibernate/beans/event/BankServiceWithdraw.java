package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.IBankServiceWithdraw;

@Entity
@DiscriminatorValue("BankServiceWithdraw")
public class BankServiceWithdraw extends PlayerEvent implements
		IBankServiceWithdraw
{
	{
		this.setType(TYPE);
	}

	@Basic
	private Long amount;

	public Long getAmount()
	{
		return amount;
	}

	public void setAmount(Long amount)
	{
		this.amount = amount;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "';'amount':" + amount + "'";
	}

}

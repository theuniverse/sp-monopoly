package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.IBuyPropertyAsk;

@Entity
@DiscriminatorValue("BuyPropertyAsk")
public class BuyPropertyAsk extends PlayerEvent implements IBuyPropertyAsk
{
	@Basic
	private int field;

	@Basic
	private Long cost;
	{
		this.setType(TYPE);
	}

	public int getField()
	{
		return field;
	}

	public void setField(int field)
	{
		this.field = field;
	}

	public Long getCost()
	{
		return cost;
	}

	public void setCost(Long cost)
	{
		this.cost = cost;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "';'field:'" + field + "';'cost':" + cost + "'";
	}

}

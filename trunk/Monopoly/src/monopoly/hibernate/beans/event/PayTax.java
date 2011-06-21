package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import monopoly.core.beans.IPlayer;
import monopoly.core.beans.event.IPayTax;
import monopoly.hibernate.beans.Player;

@Entity
@DiscriminatorValue("PayTax")
public class PayTax extends PlayerEvent implements IPayTax
{
	@ManyToOne(targetEntity = Player.class)
	private IPlayer landlord;

	@Basic
	private Long tax;

	{
		this.setType(TYPE);
	}

	public IPlayer getLandlord()
	{
		return landlord;
	}

	public void setLandlord(IPlayer landlord)
	{
		this.landlord = landlord;
	}

	public Long getTax()
	{
		return tax;
	}

	public void setTax(Long tax)
	{
		this.tax = tax;
	}

	public String toString()
	{
		return "'type':'" + TYPE + "';'player':'" + getPlayer().getColor()
				+ "';'landlord:'" + landlord.getColor() + "';'tax':" + tax
				+ "'";
	}
}

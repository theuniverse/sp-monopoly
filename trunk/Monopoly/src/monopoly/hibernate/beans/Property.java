package monopoly.hibernate.beans;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import monopoly.core.beans.INormalField;
import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IProperty;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Property extends BaseBean implements IProperty
{
	@OneToOne(targetEntity = NormalField.class, cascade =
	{ CascadeType.ALL })
	private INormalField field;

	@OneToOne(targetEntity = Player.class)
	private IPlayer player;

	@Basic
	private Long value;

	public INormalField getField()
	{
		return field;
	}

	public void setField(INormalField field)
	{
		this.field = field;
	}

	public IPlayer getPlayer()
	{
		return player;
	}

	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	public Long getValue()
	{
		return value;
	}

	public void setValue(Long value)
	{
		this.value = value;
	}

}

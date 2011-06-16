package monopoly.core.beans;

import com.iiiss.spring.common.IBean;

public interface IProperty extends IBean
{

	public INormalField getField();

	public void setField(INormalField field);

	public IPlayer getPlayer();

	public void setPlayer(IPlayer player);

	public Long getValue();

	public void setValue(Long value);
}
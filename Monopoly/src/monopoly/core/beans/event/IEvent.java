package monopoly.core.beans.event;

import com.iiiss.spring.common.IBean;

public interface IEvent extends IBean
{
	public String getType();

	public void setType(String type);
}
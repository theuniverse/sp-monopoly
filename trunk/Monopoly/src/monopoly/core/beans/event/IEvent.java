package monopoly.core.beans.event;

import com.iiiss.spring.common.IBean;

import monopoly.core.beans.IEventQueue;

public interface IEvent extends IBean
{
	public String getType();

	public void setType(String type);

	public IEventQueue getEventQueue();

	public void setEventQueue(IEventQueue eventQueue);
}